package com.openthinks.festival.data.model;

import utilities.Checker;

import com.openthinks.festival.data.model.entity.FsImageEntity;

public class FsImage extends AbstractFsJson{
	private String url;
	private String caption;
	private transient String itemref;

	public FsImage() {
	}
	
	public FsImage(String url, String caption) {
		super();
		this.url = url;
		this.caption = caption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
	public String getItemref() {
		return itemref;
	}
	
	public void setItemref(String itemref) {
		this.itemref = itemref;
	}
	
	public boolean isValid(){
		//validate image url and type
		return url!=null;
	}

	@Override
	public String key() {
		return null;
	}
	
	
	public FsImageEntity toEntity(){
		FsImageEntity entity=new FsImageEntity();
		entity.setFid(getItemref());
		entity.setCaption(getCaption());
		entity.setUrl(getUrl());
		return entity;
	}
	
	
	public static FsImage valueOf(FsImageEntity entity){
		Checker.require(entity).notNull();
		FsImage image=new FsImage();
		image.setCaption(entity.getCaption());
		image.setItemref(entity.getFid());
		image.setUrl(entity.getUrl());
		return image;
	}

	@Override
	public String toString() {
		return "FsImage [url=" + url + ", caption=" + caption + ", itemref="
				+ itemref + "]";
	}

}