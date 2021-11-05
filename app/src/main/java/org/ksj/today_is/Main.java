package org.ksj.today_is;

import java.util.Date;

public class Main {
    Integer _id, Check_name;
    String Date_name, Group_name, Contents, Place, Start_time, Notice_time;//Date

    public Main(Integer _id, Integer check_name, String date_name, String group_name, String contents, String place, String start_time, String notice_time) {
        this._id = _id;
        Check_name = check_name;
        Date_name = date_name;
        Group_name = group_name;
        Contents = contents;
        Place = place;
        Start_time = start_time;
        Notice_time = notice_time;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getCheck_name() {
        return Check_name;
    }

    public void setCheck_name(Integer check_name) {
        Check_name = check_name;
    }

    public String getDate_name() {
        return Date_name;
    }

    public void setDate_name(String date_name) {
        Date_name = date_name;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String group_name) {
        Group_name = group_name;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getStart_time() {
        return Start_time;
    }

    public void setStart_time(String start_time) {
        Start_time = start_time;
    }

    public String getNotice_time() {
        return Notice_time;
    }

    public void setNotice_time(String notice_time) {
        Notice_time = notice_time;
    }
}
