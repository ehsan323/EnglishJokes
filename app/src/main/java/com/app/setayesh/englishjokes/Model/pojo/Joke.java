
package com.app.setayesh.englishjokes.Model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@Entity
public class Joke {


    public Joke(int jid, String mType, List<Value> mValue) {
        this.jid = jid;
        this.mType = mType;
        this.mValue = mValue;
    }

    @PrimaryKey(autoGenerate = true)
    private int jid;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String mType;
    @SerializedName("value")
    @ColumnInfo(name = "value")
    private List<Value> mValue;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @TypeConverters(Joke.class)
    public List<Value> getValue() {
        return mValue;
    }

    public void setValue(List<Value> value) {
        mValue = value;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }
}
