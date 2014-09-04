package com.openthinks.festival.data.model;

import java.util.ArrayList;
import java.util.List;

import utilities.CommonUtilities;

public class FsItem extends AbstractFsJson{
	private String name;
	private String date;
	
	private transient String month;
	private transient String countrycode;
	
	private String desc;
	private List<FsImage> images = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setImages(List<FsImage> images) {
		this.images = images;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void add(FsImage image) {
		images.add(image);
		String key;
		try {
			key = key();
			image.setItemref(key);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
	}


	@Override
	public String toString() {
		return "[countrycode=" + countrycode + ", month=" + month
				+ ", date=" + date + ", name=" + name + "]";
	}

	public int getDateNumber() {
		int number=0;
		try {
			number = Integer.valueOf(getDate());
		} catch (NumberFormatException e) {
			//
		}
		return number;
	}

	@Override
	public String key() {
		
		StringBuilder buider=new StringBuilder();
		
		buider.append(CommonUtilities.format(Integer.valueOf(getCountrycode()), 4, 0));
		buider.append("-");
		buider.append(CommonUtilities.format(Integer.valueOf(getMonth()), 2, 0));
		buider.append("-");
		buider.append(CommonUtilities.format(Integer.valueOf(getDate()), 2, 0));
		buider.append("-");
		buider.append(getName());
		
		return buider.toString();
	}

}