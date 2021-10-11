package com.example.appnhac.Model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat implements Parcelable {
    @SerializedName("Idbaihat")
    @Expose
    private String idBaihat;

    @SerializedName("Tenbaihat")
    @Expose
    private String tenbaihat;

    @SerializedName("Hinhbaihat")
    @Expose
    private String hinhbaihat;

    @SerializedName("Casi")
    @Expose
    private String casi;

    @SerializedName("Linkbaihat")
    @Expose
    private String linkbaihat;

    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    protected Baihat(Parcel in) {
        idBaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

    public String getIdBaihat() {
        return idBaihat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public String getCasi() {
        return casi;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public String getLuotthich() {
        return luotthich;
    }

    public void setIdBaihat(String idBaihat) {
        this.idBaihat = idBaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        this.hinhbaihat = hinhbaihat;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public void setLinkbaihat(String linkbaihat) {
        this.linkbaihat = linkbaihat;
    }

    public void setLuotthich(String luotthich) {
        this.luotthich = luotthich;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBaihat);
        dest.writeString(tenbaihat);
        dest.writeString(hinhbaihat);
        dest.writeString(casi);
        dest.writeString(linkbaihat);
        dest.writeString(luotthich);
    }
}
