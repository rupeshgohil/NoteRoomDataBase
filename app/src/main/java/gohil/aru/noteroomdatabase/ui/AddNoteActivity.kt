package gohil.aru.noteroomdatabase.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import com.google.gson.Gson
import gohil.aru.noteroomdatabase.BaseActivity
import gohil.aru.noteroomdatabase.R
import gohil.aru.noteroomdatabase.listener.ResponseListener
import gohil.aru.noteroomdatabase.modal.Note
import gohil.aru.noteroomdatabase.repositery.NoteReposetory
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : BaseActivity(), AdapterView.OnItemSelectedListener,ResponseListener{



    var strMarried_status:String?= "Married"
    var mArrayIdentity: ArrayList<String>? = ArrayList()
    var strGender:String?= "Male"
    var strCategory:String?= null
     var notereposetory: NoteReposetory? =null
    var response:Long? = null
    var mNote = Note()
    var isUpdate:Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        intent = intent
        mArrayIdentity!!.add("Select Identity")
        mArrayIdentity!!.add("Aadhar card")
        mArrayIdentity!!.add("Voter card")
        mArrayIdentity!!.add("Passport")
        mArrayIdentity!!.add("Driving Licence")
        if(intent != null){
            if(intent.getStringExtra("Edit").equals("true",ignoreCase = true)){
                mNote = intent.getSerializableExtra("note") as Note
                edtFirstname.setText(mNote.firstname)
                edtLastname.setText(mNote.lastname)
                edtAge.setText(mNote.age)
                edtCity.setText(mNote.city)
                edtmobile.setText(mNote.mobileNumber)
                if(mNote.gender.equals("Male",ignoreCase = true)){
                    strGender = "Male"
                    rbmale.isChecked = true
                    rbfemale.isChecked =false
                }else{
                    strGender = "Female"
                    rbmale.isChecked = false
                    rbfemale.isChecked =true
                }
                if(mNote.marriedStatus.equals("Married",ignoreCase = true)){
                    cbmarried.isChecked =true
                    cbunmarried.isChecked =false
                    strMarried_status = "Married"
                }else{
                    cbmarried.isChecked =false
                    cbunmarried.isChecked =true
                    strMarried_status = "UnMarried"
                }
                if(mNote.identity.equals("Aadhar card",ignoreCase = true)){
                    spinnercategory.setSelection(0)
                    strCategory = mArrayIdentity!!.get(1)
                }else if(mNote.identity.equals("Voter card",ignoreCase = true)){
                    spinnercategory.setSelection(2)
                    strCategory = mArrayIdentity!!.get(2)
                }else if(mNote.identity.equals("Passport",ignoreCase = true)){
                    spinnercategory.setSelection(3)
                    strCategory = mArrayIdentity!!.get(3)
                }else if(mNote.identity.equals("Driving Licence",ignoreCase = true)){
                    spinnercategory.setSelection(4)
                    strCategory = mArrayIdentity!!.get(4)
                }
                btnsave.setText("Update")
                isUpdate =true
            }
        }
        notereposetory =  NoteReposetory(this@AddNoteActivity)
        notereposetory!!.setListener(this@AddNoteActivity)
        gender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->

                if (checkedId == R.id.rbmale) {
                    strGender = "Male"
                    Log.e("Gender",strGender)
                } else {
                    strGender = "Female"
                    Log.e("Gender",strGender)

                }

            })

        spinnercategory!!.onItemSelectedListener =this
        spinnercategory.adapter = ArrayAdapter(this@AddNoteActivity,android.R.layout.simple_spinner_item,mArrayIdentity!!)
        cbmarried.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                cbunmarried.isChecked =false
                strMarried_status = "Married"
            }else{
                cbunmarried.isChecked =true
            }
        }
        cbunmarried.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                cbmarried.isChecked =false
                strMarried_status = "UnMarried"
            }else{
                cbmarried.isChecked =true
            }
        }
        btnsave.setOnClickListener {
            if(isvalidField()){

                if(isUpdate){

                     notereposetory!!.UpdateTask(mNote.id,edtFirstname.text.toString().trim(),
                        edtLastname.text.toString().trim(),
                        edtAge.text.toString().trim(),
                        edtCity.text.toString().trim(),
                        edtmobile.text.toString().trim(),
                        strCategory,
                        strGender,strMarried_status)
                }else{
                    notereposetory!!.InsetTask(edtFirstname.text.toString().trim(),
                        edtLastname.text.toString().trim(),
                        edtAge.text.toString().trim(),
                        edtCity.text.toString().trim(),
                        edtmobile.text.toString().trim(),
                        strCategory,
                        strGender,strMarried_status)
                }

            }
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            gohil.aru.noteroomdatabase.R.id.spinnercategory -> {
                Log.e("selected", mArrayIdentity!!.get(position) + "null")
                strCategory = mArrayIdentity!!.get(position)
                Log.e("Category:",strCategory)
            }

        }
    }
    private fun isvalidField(): Boolean {
        if(TextUtils.isEmpty(edtFirstname.text.toString().trim())){
            showToast(applicationContext,"Enter First name")
            return  false
        }else if(TextUtils.isEmpty(edtLastname.text.toString().trim())){
            showToast(applicationContext,"Enter Last name")
            return  false
        }else if(TextUtils.isEmpty(edtAge.text.toString().trim())){
            showToast(applicationContext,"Enter Age")
            return  false
        }else if(edtAge.text.toString().toInt() > 100){
            showToast(applicationContext,"Enter Age less then 100")
            return  false
        }else if(TextUtils.isEmpty(edtCity.text.toString().trim())){
            showToast(applicationContext,"Enter City")
            return  false
        }else if(TextUtils.isEmpty(edtmobile.text.toString().trim())){
            showToast(applicationContext,"Enter Mobile number")
            return  false
        }else if(strCategory.equals("Select Identity",ignoreCase = true)){
            showToast(applicationContext,"Select Identity")
            return  false
        }else{
            return true
        }

    }
    override fun onResponse(status: Long) {
        Log.e("Call:2","call")
        if(status!!.toInt() != -1){

            var mIntent = Intent()
            setResult(200,mIntent)
            finish()
        }

    }
}
