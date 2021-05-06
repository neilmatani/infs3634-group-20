package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Note WHERE User == :user AND Module == :module")
    Note getNotes(String user, String module);

    @Insert
    void insertNote(Note note);

    @Delete//("DELETE FROM Note WHERE User == :user AND Module == :module")
    void deleteNote(Note note);
}
