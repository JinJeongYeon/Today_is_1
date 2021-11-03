package org.ksj.today_is;

import java.util.Date;

public class Main {
    Integer _id;
    Date Date_name;
    String Group_name;
    String Contents;
    Integer Check;
    String Place;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Date getDate_name() {
        return Date_name;
    }

    public void setDate_name(Date date_name) {
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

    public Integer getCheck() {
        return Check;
    }

    public void setCheck(Integer check) {
        Check = check;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }
}
