package com.blue_b.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationModel implements Parcelable {
    String image_url,title_en,title_local,des_en,des_local,notification_type,item_id,time_stamp,optional_en,optional_local,open_or_not;

    public NotificationModel(String image_url, String title_en, String title_local, String des_en, String des_local, String notification_type, String item_id, String time_stamp, String optional_en, String optional_local, String open_or_not) {
        this.image_url = image_url;
        this.title_en = title_en;
        this.title_local = title_local;
        this.des_en = des_en;
        this.des_local = des_local;
        this.notification_type = notification_type;
        this.item_id = item_id;
        this.time_stamp = time_stamp;
        this.optional_en = optional_en;
        this.optional_local = optional_local;
        this.open_or_not = open_or_not;
    }

    protected NotificationModel(Parcel in) {
        image_url = in.readString();
        title_en = in.readString();
        title_local = in.readString();
        des_en = in.readString();
        des_local = in.readString();
        notification_type = in.readString();
        item_id = in.readString();
        time_stamp = in.readString();
        optional_en = in.readString();
        optional_local = in.readString();
        open_or_not = in.readString();
    }

    public static final Creator<NotificationModel> CREATOR = new Creator<NotificationModel>() {
        @Override
        public NotificationModel createFromParcel(Parcel in) {
            return new NotificationModel(in);
        }

        @Override
        public NotificationModel[] newArray(int size) {
            return new NotificationModel[size];
        }
    };

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_local() {
        return title_local;
    }

    public void setTitle_local(String title_local) {
        this.title_local = title_local;
    }

    public String getDes_en() {
        return des_en;
    }

    public void setDes_en(String des_en) {
        this.des_en = des_en;
    }

    public String getDes_local() {
        return des_local;
    }

    public void setDes_local(String des_local) {
        this.des_local = des_local;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getOptional_en() {
        return optional_en;
    }

    public void setOptional_en(String optional_en) {
        this.optional_en = optional_en;
    }

    public String getOptional_local() {
        return optional_local;
    }

    public void setOptional_local(String optional_local) {
        this.optional_local = optional_local;
    }

    public String getOpen_or_not() {
        return open_or_not;
    }

    public void setOpen_or_not(String open_or_not) {
        this.open_or_not = open_or_not;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image_url);
        dest.writeString(title_en);
        dest.writeString(title_local);
        dest.writeString(des_en);
        dest.writeString(des_local);
        dest.writeString(notification_type);
        dest.writeString(item_id);
        dest.writeString(time_stamp);
        dest.writeString(optional_en);
        dest.writeString(optional_local);
        dest.writeString(open_or_not);
    }
}
