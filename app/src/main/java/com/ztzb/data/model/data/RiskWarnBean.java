package com.ztzb.data.model.data;

public class RiskWarnBean {

    private Integer id;
    private String code;
    private String type;
    private String describe;
    private Integer close_warning;
    private Integer disappear_warning;
    private String factors;
    private String level;
    private String event;
    private String construction_scheme;
    private Long createtime;
    private String dun_name;
    private String dun_size;
    private String dun_code;
    private Integer dun_yue_code;
    private String dun_jue_area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Integer getClose_warning() {
        return close_warning;
    }

    public void setClose_warning(Integer close_warning) {
        this.close_warning = close_warning;
    }

    public Integer getDisappear_warning() {
        return disappear_warning;
    }

    public void setDisappear_warning(Integer disappear_warning) {
        this.disappear_warning = disappear_warning;
    }

    public String getConstruction_scheme() {
        return construction_scheme;
    }

    public void setConstruction_scheme(String construction_scheme) {
        this.construction_scheme = construction_scheme;
    }

    public String getDun_name() {
        return dun_name;
    }

    public void setDun_name(String dun_name) {
        this.dun_name = dun_name;
    }

    public String getDun_size() {
        return dun_size;
    }

    public void setDun_size(String dun_size) {
        this.dun_size = dun_size;
    }

    public String getDun_code() {
        return dun_code;
    }

    public void setDun_code(String dun_code) {
        this.dun_code = dun_code;
    }

    public Integer getDun_yue_code() {
        return dun_yue_code;
    }

    public void setDun_yue_code(Integer dun_yue_code) {
        this.dun_yue_code = dun_yue_code;
    }

    public String getDun_jue_area() {
        return dun_jue_area;
    }

    public void setDun_jue_area(String dun_jue_area) {
        this.dun_jue_area = dun_jue_area;
    }
}
