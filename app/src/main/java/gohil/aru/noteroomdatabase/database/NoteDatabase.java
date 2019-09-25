package gohil.aru.noteroomdatabase.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import gohil.aru.noteroomdatabase.dao.DaoAccess;
import gohil.aru.noteroomdatabase.modal.Note;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract  class NoteDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
