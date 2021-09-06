package com.ztzb.data.model.data;

import java.io.Serializable;
import java.util.List;

public class MonitorPointDetailBean implements Serializable {


    private String msg;
    private Integer code;
    private Integer count;
    private List<PointDetail> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PointDetail> getData() {
        return data;
    }

    public void setData(List<PointDetail> data) {
        this.data = data;
    }

    public static class PointDetail implements Serializable {
        private Integer SensorDataID;
        private String SensorCode;
        private Integer ID;
        private Integer ProjectID;
        private String MeaTime;
        private Double MeaValue1;
        private Double MeaValue2;
        private Object MeaValue3;
        private Double ResValue1;
        private Double ResValue2;
        private Object ResValue3;
        private Object Valid;
        private Integer Status;
        private Integer Origin;
        private String Remark;
        private Double ExtraValue;
        private Object ErrorStatus;
        private String LinkID;
        private Object ResValue4;

        public Integer getSensorDataID() {
            return SensorDataID;
        }

        public void setSensorDataID(Integer SensorDataID) {
            this.SensorDataID = SensorDataID;
        }

        public String getSensorCode() {
            return SensorCode;
        }

        public void setSensorCode(String SensorCode) {
            this.SensorCode = SensorCode;
        }

        public Integer getID() {
            return ID;
        }

        public void setID(Integer ID) {
            this.ID = ID;
        }

        public Integer getProjectID() {
            return ProjectID;
        }

        public void setProjectID(Integer ProjectID) {
            this.ProjectID = ProjectID;
        }

        public String getMeaTime() {
            return MeaTime;
        }

        public void setMeaTime(String MeaTime) {
            this.MeaTime = MeaTime;
        }

        public Double getMeaValue1() {
            return MeaValue1;
        }

        public void setMeaValue1(Double MeaValue1) {
            this.MeaValue1 = MeaValue1;
        }

        public Double getMeaValue2() {
            return MeaValue2;
        }

        public void setMeaValue2(Double MeaValue2) {
            this.MeaValue2 = MeaValue2;
        }

        public Object getMeaValue3() {
            return MeaValue3;
        }

        public void setMeaValue3(Object MeaValue3) {
            this.MeaValue3 = MeaValue3;
        }

        public Double getResValue1() {
            return ResValue1;
        }

        public void setResValue1(Double ResValue1) {
            this.ResValue1 = ResValue1;
        }

        public Double getResValue2() {
            return ResValue2;
        }

        public void setResValue2(Double ResValue2) {
            this.ResValue2 = ResValue2;
        }

        public Object getResValue3() {
            return ResValue3;
        }

        public void setResValue3(Object ResValue3) {
            this.ResValue3 = ResValue3;
        }

        public Object getValid() {
            return Valid;
        }

        public void setValid(Object Valid) {
            this.Valid = Valid;
        }

        public Integer getStatus() {
            return Status;
        }

        public void setStatus(Integer Status) {
            this.Status = Status;
        }

        public Integer getOrigin() {
            return Origin;
        }

        public void setOrigin(Integer Origin) {
            this.Origin = Origin;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public Double getExtraValue() {
            return ExtraValue;
        }

        public void setExtraValue(Double ExtraValue) {
            this.ExtraValue = ExtraValue;
        }

        public Object getErrorStatus() {
            return ErrorStatus;
        }

        public void setErrorStatus(Object ErrorStatus) {
            this.ErrorStatus = ErrorStatus;
        }

        public String getLinkID() {
            return LinkID;
        }

        public void setLinkID(String LinkID) {
            this.LinkID = LinkID;
        }

        public Object getResValue4() {
            return ResValue4;
        }

        public void setResValue4(Object ResValue4) {
            this.ResValue4 = ResValue4;
        }
    }
}
