package com.mile.android.entities;

/**
 * Created by yacovyitzhak on 17/06/2017.
 */

public class Group {
    private String AdminName;
    private String ExpireDate;   //>2017-07-14T00:00:00</ExpireDate>
    private String From;         //>Haifa</From>
    private Long GroupId;        //1</GroupId>
    private String Name ;        //>TheGroup</Name>
    private String To;            //To>Tel Aviv</To>
    private Integer Type;         //>1</Type>

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
