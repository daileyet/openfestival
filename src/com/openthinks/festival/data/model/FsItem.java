package com.openthinks.festival.data.model;

import java.util.ArrayList;
import java.util.List;

public class FsItem extends AbstractFsJson{
	private String name;
	private String date;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void add(FsImage image) {
		images.add(image);
	}

	
	
	@Override
	public String toString() {
		return "FsItem [name=" + name + ", date=" + date + "]";
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

}