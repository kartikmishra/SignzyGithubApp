package com.example.signzygithubapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReposModel implements Parcelable {

    private Integer id;

    private String nodeId;

    private String name;

    private String full_name;

    private Boolean _private;


    private String html_url;

    private Object description;


    protected ReposModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nodeId = in.readString();
        name = in.readString();
        full_name = in.readString();
        byte tmp_private = in.readByte();
        _private = tmp_private == 0 ? null : tmp_private == 1;
        html_url = in.readString();
    }

    public static final Creator<ReposModel> CREATOR = new Creator<ReposModel>() {
        @Override
        public ReposModel createFromParcel(Parcel in) {
            return new ReposModel(in);
        }

        @Override
        public ReposModel[] newArray(int size) {
            return new ReposModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String fullName) {
        this.full_name = fullName;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }



    public String getHtmlUrl() {
        return html_url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.html_url = htmlUrl;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(nodeId);
        parcel.writeString(name);
        parcel.writeString(full_name);
        parcel.writeByte((byte) (_private == null ? 0 : _private ? 1 : 2));
        parcel.writeString(html_url);
    }
}
