package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Note {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int noteId;
    @SerializedName("User")
    @Expose
    @NonNull
    private String user;
    @SerializedName("Module")
    @Expose
    @NonNull
    private String module;
    @SerializedName("Note")
    @Expose
    @NonNull
    private String note;


    public Note( String user, String module, String note) {
        this.user = user;
        this.module = module;
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



}
