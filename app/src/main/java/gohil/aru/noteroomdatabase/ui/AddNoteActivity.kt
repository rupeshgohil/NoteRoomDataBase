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
import gohil.aru.noteroomdatabase.BaseActivity
import gohil.aru.noteroomdatabase.R
import gohil.aru.noteroomdatabase.repositery.NoteReposetory
import gohil.aru.noteroomdatabase.repositery.NoteReposetory.status
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : BaseActivity(), AdapterView.OnItemSelectedListener {


    var strMarried_status:String?= "Married"
    var mArrayIdentity: ArrayList<String>? = ArrayList()
    var strGender:String?= "Male"
    var strCategory:String?= null
     var notereposetory: NoteReposetory? =null
    var insetstatus:Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        notereposetory =  NoteReposetory(getApplicationContext())

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
        mArrayIdentity!!.add("Select Identity")
        mArrayIdentity!!.add("Aadhar card")
        mArrayIdentity!!.add("Voter card")
        mArrayIdentity!!.add("Passport")
        mArrayIdentity!!.add("Driving Licence")
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
                notereposetory!!.InsetTask(edtFirstname.text.toString().trim(),
                    edtLastname.text.toString().trim(),
                    edtAge.text.toString().trim(),
                    edtCity.text.toString().trim(),
                    edtmobile.text.toString().trim(),
                    strCategory,
                    strGender,strMarried_status)

                Handler().postDelayed({
                    Log.e("StatusUpdateCode==>", "Null"+status)

                    if(status.toInt() != 0){
                        Log.e("Call:2","call")
                        var mIntent = Intent()
                        setResult(200,mIntent)
                        finish()
                    }
                }, 2000)

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
}
