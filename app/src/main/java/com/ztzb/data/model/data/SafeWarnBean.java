package com.ztzb.data.model.data;

import java.io.Serializable;

public class SafeWarnBean implements Serializable {


    private Integer EventID;
    private String EventName;
    private Integer ProjectSiteID;
    private Integer GradeID;
    private String Time;
    private String Describe;
    private String UpdateTime;
    private Integer UserID;
    private Integer WarnType;
    private String GradeName;
    private Integer Status;
    private String SiteName;
    private String SiteNames;
    private Integer FileNum;
    private String RealName;

    public Integer getEventID() {
        return EventID;
    }

    public void setEventID(Integer EventID) {
        this.EventID = EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public Integer getProjectSiteID() {
        return ProjectSiteID;
    }

    public void setProjectSiteID(Integer ProjectSiteID) {
        this.ProjectSiteID = ProjectSiteID;
    }

    public Integer getGradeID() {
        return GradeID;
    }

    public void setGradeID(Integer GradeID) {
        this.GradeID = GradeID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public Integer getWarnType() {
        return WarnType;
    }

    public void setWarnType(Integer WarnType) {
        this.WarnType = WarnType;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String GradeName) {
        this.GradeName = GradeName;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String SiteName) {
        this.SiteName = SiteName;
    }

    public String getSiteNames() {
        return SiteNames;
    }

    public void setSiteNames(String SiteNames) {
        this.SiteNames = SiteNames;
    }

    public Integer getFileNum() {
        return FileNum;
    }

    public void setFileNum(Integer FileNum) {
        this.FileNum = FileNum;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }
}
