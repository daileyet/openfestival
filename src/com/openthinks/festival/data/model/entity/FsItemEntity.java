package com.openthinks.festival.data.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsitems")
public class FsItemEntity {
	@Column
	@Id
	private String fid;
	@Column
	private String name;
	@Column
	private String date;
	@Column
	private String month;
	@Column
	private String desc;
	@Column
	private String countrycode="86";
	@Column
	private String day;
	@Column
	private String week;
	@Column
	private String switcher="0";
	
	public FsItemEntity() {
	}
	
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMonth() {
		return this.month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getSwitcher() {
		return switcher;
	}
	public void setSwitcher(String switcher) {
		this.switcher = switcher;
	}


	@Override
	public String toString() {
		return "FsItemEntity [fid=" + fid + ", name=" + name + ", date=" + date
				+ ", month=" + month + ", desc=" + desc + ", countrycode="
				+ countrycode + ", day=" + day + ", week=" + week
				+ ", switcher=" + switcher + "]";
	}
	
	
}
