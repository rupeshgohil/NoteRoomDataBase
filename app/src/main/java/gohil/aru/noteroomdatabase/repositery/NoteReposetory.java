package gohil.aru.noteroomdatabase.repositery;



import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import gohil.aru.noteroomdatabase.database.NoteDatabase;
import gohil.aru.noteroomdatabase.modal.Note;

public class NoteReposetory {
    private String DB_NAME = "db_task";
    private NoteDatabase noteDatabase;
    public static  long status;
    public static  long status_update;
    public Context mContext;
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
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().InsetTask(note);
                Log.e("Status",String.valueOf(status));
                return null;
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
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().UpdateTask(note);
                Log.e("Status_update",String.valueOf(status));
                return null;
            }

        }.execute();
    }

    public void DeleteRecord(@NotNull Note note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                status =  noteDatabase.daoAccess().DeleteTask(note);
                Log.e("Status_delete",String.valueOf(status));
                return null;
            }

        }.execute();
    }
}
