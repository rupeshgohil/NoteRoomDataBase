package gohil.aru.noteroomdatabase.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import gohil.aru.noteroomdatabase.BaseActivity
import gohil.aru.noteroomdatabase.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import gohil.aru.noteroomdatabase.adapter.TaskAdapter
import gohil.aru.noteroomdatabase.modal.Note
import gohil.aru.noteroomdatabase.repositery.NoteReposetory
import gohil.aru.noteroomdatabase.repositery.NoteReposetory.status
import kotlinx.android.synthetic.main.content_main.*
import zestbrains.stintr.listener.MultiClickListener

class MainActivity : BaseActivity(), MultiClickListener {



    var notereposetory: NoteReposetory? =null
    var mAdapter: TaskAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        notereposetory =  NoteReposetory(getApplicationContext())
        fab.setOnClickListener {
            var mIntent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(mIntent,200)
        }
        updateTaskList()
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
                    val linearLayoutManager = LinearLayoutManager(this)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                       linearLayoutManager.reverseLayout = false
                    rvtasklist!!.setHasFixedSize(true)
                    rvtasklist!!.layoutManager = linearLayoutManager
                    mAdapter = TaskAdapter(this,notes)
                    rvtasklist!!.adapter = mAdapter
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
        Handler().postDelayed({
            Log.e("StatusUpdateCode==>", "Null"+ NoteReposetory.status)

            if(status.toInt() != 0){
                Log.e("Call:2","call")
                updateTaskList()
            }
        }, 2000)
    }

    private fun updateRecord(note: Note) {
        var mIntent = Intent(this,AddNoteActivity::class.java)
        mIntent.putExtra("note",note)
        mIntent.putExtra("Edit","true")
        startActivityForResult(mIntent,200)
    }

}
