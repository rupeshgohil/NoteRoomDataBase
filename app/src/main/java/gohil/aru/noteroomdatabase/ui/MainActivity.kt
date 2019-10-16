package gohil.aru.noteroomdatabase.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import gohil.aru.noteroomdatabase.BaseActivity
import gohil.aru.noteroomdatabase.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import gohil.aru.noteroomdatabase.adapter.TaskAdapter
import gohil.aru.noteroomdatabase.listener.ResponseListener
import gohil.aru.noteroomdatabase.modal.Note
import gohil.aru.noteroomdatabase.repositery.NoteReposetory
import kotlinx.android.synthetic.main.content_main.*
import zestbrains.stintr.listener.MultiClickListener

class MainActivity : BaseActivity(), MultiClickListener,ResponseListener{
    var mDialog:Dialog?=null
    var notereposetory: NoteReposetory? =null
    var mAdapter: TaskAdapter?= null
    var mList : List<Note> = ArrayList()
    internal var tvAtoZ: TextView?= null
    internal var tvok: TextView? = null
    internal var tvcancel: TextView?= null
    internal var tvZtoA: TextView?= null
    internal var tvCategory: TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notereposetory =  NoteReposetory(this@MainActivity)
        notereposetory!!.setMainListener(this@MainActivity)
        fab.setOnClickListener {
            var mIntent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(mIntent,200)
        }
        setView()
        updateTaskList()

        ivfilter.setOnClickListener {
            FiletPopup()
        }
    }
    private fun FiletPopup() {
            if(mDialog == null){
                mDialog = Dialog(this)
            }
            mDialog!!.setContentView(R.layout.layout_filter);
            mDialog!!.setCancelable(true);
            mDialog!!.getWindow()!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mDialog!!.show()
            tvAtoZ = mDialog!!.findViewById<View>(R.id.tvatoz) as TextView
            tvZtoA = mDialog!!.findViewById<View>(R.id.tvztoa) as TextView
            tvCategory = mDialog!!.findViewById<View>(R.id.tvcategory) as TextView
            tvcancel = mDialog!!.findViewById<View>(R.id.tvcancel) as TextView
            tvcancel!!.setOnClickListener {
                mDialog!!.dismiss()
            }
            tvAtoZ!!.setOnClickListener {
                getDataofAtoZ()
                 mDialog!!.dismiss()
            }
            tvZtoA!!.setOnClickListener {
                getZtoAdata()
                mDialog!!.dismiss()
            }
            tvCategory!!.setOnClickListener {
                category()
                mDialog!!.dismiss()
            }

    }

    private fun category() {
        notereposetory!!.atoZdata.observe(this,
            Observer<List<Note>> { notes ->
                if(!notes.isEmpty()){
                    mList =notes;
                    mAdapter = TaskAdapter(this,mList,true)
                    rvtasklist!!.adapter = mAdapter
                    mAdapter!!.notifyDataSetChanged()
                }
            })
    }

    private fun getZtoAdata() {
        notereposetory!!.ztoAdata.observe(this,
            Observer<List<Note>> { notes ->
                if(!notes.isEmpty()){
                    mList =notes;
                    mAdapter = TaskAdapter(this,mList,false)
                    rvtasklist!!.adapter = mAdapter
                    mAdapter!!.notifyDataSetChanged()
                }
            })
    }
    private fun getDataofAtoZ() {
        notereposetory!!.atoZdata.observe(this,
            Observer<List<Note>> { notes ->
                if(!notes.isEmpty()){
                    mList =notes;
                    mAdapter = TaskAdapter(this,mList,false)
                    rvtasklist!!.adapter = mAdapter
                    mAdapter!!.notifyDataSetChanged()
                }
            })
    }
    private fun setView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false
        rvtasklist!!.setHasFixedSize(true)
        rvtasklist!!.layoutManager = linearLayoutManager
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            updateTaskList()
        }
    }
    private fun updateTaskList() {
        notereposetory!!.tasks.observe(this,
            Observer<List<Note>> { notes ->
                if(!notes.isEmpty()){
                    mList =notes;
                    mAdapter = TaskAdapter(this,mList,false)
                    rvtasklist!!.adapter = mAdapter
                    mAdapter!!.notifyDataSetChanged()
                }
            })
    }
    override fun onMultiClick(pos: Int, note: Note, str: String) {
       if(str.equals("edit",ignoreCase = true)){
                updateRecord(note)
       }else{
           deleteRecord(note)
       }
    }
   private fun deleteRecord(note: Note) {
      notereposetory!!.DeleteRecord(note)
    }
    private fun updateRecord(note: Note) {
        var mIntent = Intent(this,AddNoteActivity::class.java)
        mIntent.putExtra("note",note)
        mIntent.putExtra("Edit","true")
        startActivityForResult(mIntent,200)
    }
    override fun onResponse(status: Long) {
        Log.e("Call","Delete")
        updateTaskList()
    }

}
