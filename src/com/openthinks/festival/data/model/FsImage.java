package com.openthinks.festival.data.model;

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
		// TODO Auto-generated method stub
		return null;
	}

}