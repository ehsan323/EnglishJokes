
package com.app.setayesh.englishjokes.Model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Joke.class,
        parentColumns = "jid",
        childColumns = "vid",
        onDelete = CASCADE))
public class Value {

    public Value(int vid, List<Object> mCategories, Long mId, String mJoke) {
        this.vid = vid;
        this.mCategories = mCategories;
        this.mId = mId;
        this.mJoke = mJoke;
    }

    @PrimaryKey(autoGenerate = true)
    private int vid;

    @SerializedName("categories")
    @ColumnInfo(name = "categories")
    private List<Object> mCategories;
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Long mId;
    @SerializedName("joke")
    @ColumnInfo(name = "joke")
    private String mJoke;

    public List<Object> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Object> categories) {
        mCategories = categories;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String joke) {
        mJoke = joke;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
}
