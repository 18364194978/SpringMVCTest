package com.lanyus.account.entity;

/**
 * Created by xie on 2018/2/24.
 */
public class Company {
    private String company_id;
    private String company_name;
    private String region_id;
    private String region_name;
    public String getCompany_id() {
        return company_id;
    }
    public void setCompany_id(String company_id) {
        this.company_id= company_id;
    }
    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name= company_name;
    }
    public String getRegion_id() {
        return region_id;
    }
    public void setRegion_id(String region_id) {
        this.region_id= region_id;
    }
    public String getRegion_name() {
        return region_name;
    }
    public void setRegion_name(String region_name) {
        this.region_name= region_name;
    }
}
