package com.android.fetchrewards.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is a model class for Items object we get from the network call. It is made parcelable for transmitting
 * data from one activity to another.
 */
public class Items implements Parcelable {

    private final int  id;
    private final int listId;
    private final String name;

    public Items(final int id, final int listId, final String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public Items(final Parcel in) {
        this.id = in.readInt();
        this.listId = in.readInt();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(listId);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        public Items[] newArray(int size) {
            return new Items[size];

        }
    };
}
