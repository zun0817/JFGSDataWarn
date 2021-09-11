package com.ztzb.data.model.data;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private Integer id;
    private Integer levelId;
    private Integer pid;
    private String phone;
    private String password;
    private String withdrawPassword;
    private String salt;
    private String nickname;
    private String realname;
    private String headPic;
    private String oauth;
    private String openid;
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;
    private Integer sex;
    private Object birthday;
    private Integer view;
    private Integer status;
    private Integer check;
    private String note;
    private String token;
    private Object enterpriseName;
    private Object job;
    private Object organization;
    private Object duty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWithdrawPassword() {
        return withdrawPassword;
    }

    public void setWithdrawPassword(String withdrawPassword) {
        this.withdrawPassword = withdrawPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(Object enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Object getJob() {
        return job;
    }

    public void setJob(Object job) {
        this.job = job;
    }

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

    public Object getDuty() {
        return duty;
    }

    public void setDuty(Object duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id=" + id +
                ", levelId=" + levelId +
                ", pid=" + pid +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", withdrawPassword='" + withdrawPassword + '\'' +
                ", salt='" + salt + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", headPic='" + headPic + '\'' +
                ", oauth='" + oauth + '\'' +
                ", openid='" + openid + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", view=" + view +
                ", status=" + status +
                ", check=" + check +
                ", note='" + note + '\'' +
                ", token='" + token + '\'' +
                ", enterpriseName=" + enterpriseName +
                ", job=" + job +
                ", organization=" + organization +
                ", duty=" + duty +
                '}';
    }
}
