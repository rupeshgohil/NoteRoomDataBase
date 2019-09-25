package gohil.aru.noteroomdatabase.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import gohil.aru.noteroomdatabase.BaseActivity
import gohil.aru.noteroomdatabase.R
import gohil.aru.noteroomdatabase.listener.AppConstant
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import gohil.aru.noteroomdatabase.adapter.TaskAdapter
import gohil.aru.noteroomdatabase.modal.Note
import gohil.aru.noteroomdatabase.repositery.NoteReposetory
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity() {
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
                       linearLayoutManager.reverseLayout = true
                    rvtasklist!!.setHasFixedSize(true)
                    rvtasklist!!.layoutManager = linearLayoutManager
                    mAdapter = TaskAdapter(this,notes)
                    rvtasklist!!.adapter = mAdapter
                }
            })

    }
}
