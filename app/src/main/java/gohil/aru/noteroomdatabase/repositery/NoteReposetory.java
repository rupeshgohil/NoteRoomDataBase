package gohil.aru.noteroomdatabase.repositery;



import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import gohil.aru.noteroomdatabase.database.NoteDatabase;
import gohil.aru.noteroomdatabase.listener.ResponseListener;
import gohil.aru.noteroomdatabase.modal.Note;
import gohil.aru.noteroomdatabase.ui.AddNoteActivity;
import gohil.aru.noteroomdatabase.ui.MainActivity;

public class NoteReposetory {
    private String DB_NAME = "db_task";
    private NoteDatabase noteDatabase;
    public  long status;
    public  int status_del;
    public static  long status_update;
    public Context mContext;
    public Note note;
    ResponseListener mResponseListener;
    public NoteReposetory(Context context) {
        this.mContext = context;
         noteDatabase = Room.databaseBuilder(context, NoteDatabase.class, DB_NAME).build();
    }

    public void InsetTask(@NotNull String firstname,
                          @NotNull String lastname,
                          @NotNull String age,
                          @NotNull String city,
                          @NotNull String mobile,
                          @Nullable String strCategory,
                          @Nullable String strGender,
                          @Nullable String strmarriedStatus) {
        final Note note= new Note();
        note.setFirstname(firstname);
        note.setLastname(lastname);
        note.setAge(age);
        note.setCity(city);
        note.setMobileNumber(mobile);
        note.setIdentity(strCategory);
        note.setGender(strGender);
        note.setMarriedStatus(strmarriedStatus);
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().InsetTask(note);

                return status;
            }

            @Override
            protected void onPostExecute(Long aVoid) {
                super.onPostExecute(aVoid);
                Log.e("Status",String.valueOf(aVoid));
                mResponseListener.onResponse(aVoid);
            }
        }.execute();

    }
    public LiveData<List<Note>> getTasks() {
        return noteDatabase.daoAccess().fetchAlldata();
    }

    public void UpdateTask(
            @NotNull int id,
            @NotNull String firstname,
                          @NotNull String lastname,
                          @NotNull String age,
                          @NotNull String city,
                          @NotNull String mobile,
                          @Nullable String strCategory,
                          @Nullable String strGender,
                          @Nullable String strmarriedStatus) {
        final Note note= new Note();
        note.setFirstname(firstname);
        note.setId(id);
        note.setLastname(lastname);
        note.setAge(age);
        note.setCity(city);
        note.setMobileNumber(mobile);
        note.setIdentity(strCategory);
        note.setGender(strGender);
        note.setMarriedStatus(strmarriedStatus);
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().UpdateTask(note);
                Log.e("Status_update",String.valueOf(status));

                return status;
            } @Override
            protected void onPostExecute(Long aVoid) {
                super.onPostExecute(aVoid);
                mResponseListener.onResponse(aVoid);
            }

        }.execute();

    }

    public void DeleteRecord(@NotNull Note note) {
     new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().DeleteTask(note);
                return status;
            } @Override
              protected void onPostExecute(Long aVoid) {
             super.onPostExecute(aVoid);
             Log.e("Status_delete",String.valueOf(aVoid));
             mResponseListener.onResponse(aVoid);
         }

        }.execute();


    }

    public void setListener(@NotNull AddNoteActivity addNoteActivity) {
        this.mResponseListener = addNoteActivity;
    }
    public void setMainListener(@NotNull MainActivity mainListener) {
        this.mResponseListener = mainListener;
    }

    public LiveData<List<Note>> getAtoZdata() {
        return noteDatabase.daoAccess().AtoZdata();
    }
    public LiveData<List<Note>> getZtoAdata() {
        return noteDatabase.daoAccess().ZtoAdata();
    }
}
