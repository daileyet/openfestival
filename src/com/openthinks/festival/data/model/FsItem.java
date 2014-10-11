package com.openthinks.festival.data.model;

import java.util.ArrayList;
import java.util.List;

import utilities.Checker;
import utilities.CommonUtilities;

import com.openthinks.festival.data.model.entity.FsImageEntity;
import com.openthinks.festival.data.model.entity.FsItemEntity;

public class FsItem extends AbstractFsJson {
	private String name;
	private String date;

	private transient FsMonthType month;
	private transient String countrycode = "86";

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

	public List<FsImage> getImages() {
		return images;
	}

	public void setImages(List<FsImage> images) {
		this.images = images;
		for(FsImage image : images){
			image.setItemref(key());
		}
	}

	public void setDate(String date) {
		this.date = date;
	}

	public FsMonthType getMonth() {
		return month;
	}

	public void setMonth(FsMonthType month) {
		this.month = month;
	}

	public void setMonth(String month) {
		Checker.require(month).notNull();
		this.month = FsMonthType.valueOf(month);
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
			// e.printStackTrace();
		}

	}


	
	@Override
	public String toString() {
		return "FsItem [name=" + name + ", date=" + date + ", month=" + month
				+ ", countrycode=" + countrycode + ", desc=" + desc
				+ ", images=" + images + "]";
	}

	public int getDateNumber() {
		int number = 0;
		try {
			number = Integer.valueOf(getDate());
		} catch (NumberFormatException e) {
			//
		}
		return number;
	}

	@Override
	public String key() {
		Checker.require(getCountrycode()).notNull();
		Checker.require(getMonth()).notNull();
		Checker.require(getDate()).notNull();
		StringBuilder buider = new StringBuilder();

		buider.append(CommonUtilities.format(Integer.valueOf(getCountrycode()),
				4, 0));
		buider.append("-");
		buider.append(CommonUtilities.format(getMonth().ordinal(), 2, 0));
		buider.append("-");
		buider.append(CommonUtilities.format(Integer.valueOf(getDate()), 2, 0));
		buider.append("-");
		buider.append(getName());

		return buider.toString();
	}

	public FsItemEntity toEntity() {
		Checker.require(getMonth()).notNull();
		FsItemEntity entity = new FsItemEntity();
		entity.setName(getName());
		entity.setDate(getDate());
		entity.setMonth(getMonth().toString());
		entity.setDesc(getDesc());
		if (getCountrycode() != null)
			entity.setCountrycode(getCountrycode());
		entity.setDay(null);
		entity.setWeek(null);
		entity.setSwitcher("0");
		entity.setFid(key());
		return entity;
	}

	public void setEntityImages(List<FsImageEntity> imgEntityList) {
		for (FsImageEntity entity : CommonUtilities
				.requireNotNull(imgEntityList)) {
			add(FsImage.valueOf(entity));
		}
	}

	public static FsItem valueOf(FsItemEntity entity) {
		Checker.require(entity).notNull();
		FsItem item = new FsItem();

		item.setName(entity.getName());
		item.setCountrycode(entity.getCountrycode());
		item.setDate(entity.getDate());
		item.setDesc(entity.getDesc());
		item.setMonth(entity.getMonth());

		return item;

	}

}