package gohil.aru.noteroomdatabase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import gohil.aru.noteroomdatabase.modal.Note;

@Dao
public interface DaoAccess {
    @Insert
    long InsetTask(Note note);
    @Update
    int UpdateTask(Note note);
    @Delete
    int DeleteTask(Note note);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> fetchAlldata();

    @Query("SELECT * FROM Note WHERE id =:id")
    LiveData<Note> getSingleTask(int id);
    @Query("SELECT * FROM note ORDER BY Firstname ASC")
    LiveData<List<Note>> AtoZdata();
    @Query("SELECT * FROM note ORDER BY Firstname DESC")
    LiveData<List<Note>> ZtoAdata();
}
