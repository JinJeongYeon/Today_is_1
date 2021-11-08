package org.ksj.today_is;

public class Main_group {
    Integer _id;
    String Group_name;

    public Main_group(Integer _id, String group_name) {
        this._id = _id;
        Group_name = group_name;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String group_name) {
        Group_name = group_name;
    }
}
