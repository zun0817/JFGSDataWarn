package com.ztzb.data.model.data;

import java.io.Serializable;
import java.util.List;

public class SafeMoitorPointBean {


    private Integer total;
    private List<DataDTO> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        private Integer TotalCount;
        private Integer ID;
        private String SensorAlias;
        private String SensorCode;
        private Integer SensorTypeID;
        private Integer MeaStatus;
        private String MeaFrequency;
        private String LayLocation;
        private String InstrumentModel;
        private String Factory;
        private String FactoryNumber;
        private String Range;
        private String LocationX;
        private String LocationY;
        private String LocationZ;
        private Integer MeaClass;
        private Long Time1;
        private Long Time2;
        private String Remark;
        private String SensorTypeName;
        private String SiteName;
        private String SiteNames;
        private Integer SortOrderBy;
        private Integer Orderby;
        private String MeaFrequency2;
        private Integer IsGroup;
        private String MaxTime;

        public Integer getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(Integer TotalCount) {
            this.TotalCount = TotalCount;
        }

        public Integer getID() {
            return ID;
        }

        public void setID(Integer ID) {
            this.ID = ID;
        }

        public String getSensorAlias() {
            return SensorAlias;
        }

        public void setSensorAlias(String SensorAlias) {
            this.SensorAlias = SensorAlias;
        }

        public String getSensorCode() {
            return SensorCode;
        }

        public void setSensorCode(String SensorCode) {
            this.SensorCode = SensorCode;
        }

        public Integer getSensorTypeID() {
            return SensorTypeID;
        }

        public void setSensorTypeID(Integer SensorTypeID) {
            this.SensorTypeID = SensorTypeID;
        }

        public Integer getMeaStatus() {
            return MeaStatus;
        }

        public void setMeaStatus(Integer MeaStatus) {
            this.MeaStatus = MeaStatus;
        }

        public String getMeaFrequency() {
            return MeaFrequency;
        }

        public void setMeaFrequency(String MeaFrequency) {
            this.MeaFrequency = MeaFrequency;
        }

        public String getLayLocation() {
            return LayLocation;
        }

        public void setLayLocation(String LayLocation) {
            this.LayLocation = LayLocation;
        }

        public String getInstrumentModel() {
            return InstrumentModel;
        }

        public void setInstrumentModel(String InstrumentModel) {
            this.InstrumentModel = InstrumentModel;
        }

        public String getFactory() {
            return Factory;
        }

        public void setFactory(String Factory) {
            this.Factory = Factory;
        }

        public String getFactoryNumber() {
            return FactoryNumber;
        }

        public void setFactoryNumber(String FactoryNumber) {
            this.FactoryNumber = FactoryNumber;
        }

        public String getRange() {
            return Range;
        }

        public void setRange(String Range) {
            this.Range = Range;
        }

        public String getLocationX() {
            return LocationX;
        }

        public void setLocationX(String LocationX) {
            this.LocationX = LocationX;
        }

        public String getLocationY() {
            return LocationY;
        }

        public void setLocationY(String LocationY) {
            this.LocationY = LocationY;
        }

        public String getLocationZ() {
            return LocationZ;
        }

        public void setLocationZ(String LocationZ) {
            this.LocationZ = LocationZ;
        }

        public Integer getMeaClass() {
            return MeaClass;
        }

        public void setMeaClass(Integer MeaClass) {
            this.MeaClass = MeaClass;
        }

        public Long getTime1() {
            return Time1;
        }

        public void setTime1(Long Time1) {
            this.Time1 = Time1;
        }

        public Long getTime2() {
            return Time2;
        }

        public void setTime2(Long Time2) {
            this.Time2 = Time2;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getSensorTypeName() {
            return SensorTypeName;
        }

        public void setSensorTypeName(String SensorTypeName) {
            this.SensorTypeName = SensorTypeName;
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

        public Integer getSortOrderBy() {
            return SortOrderBy;
        }

        public void setSortOrderBy(Integer SortOrderBy) {
            this.SortOrderBy = SortOrderBy;
        }

        public Integer getOrderby() {
            return Orderby;
        }

        public void setOrderby(Integer Orderby) {
            this.Orderby = Orderby;
        }

        public String getMeaFrequency2() {
            return MeaFrequency2;
        }

        public void setMeaFrequency2(String MeaFrequency2) {
            this.MeaFrequency2 = MeaFrequency2;
        }

        public Integer getIsGroup() {
            return IsGroup;
        }

        public void setIsGroup(Integer IsGroup) {
            this.IsGroup = IsGroup;
        }

        public String getMaxTime() {
            return MaxTime;
        }

        public void setMaxTime(String MaxTime) {
            this.MaxTime = MaxTime;
        }
    }
}
