package com.openthinks.festival.data.model;

import java.util.Comparator;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Collection for festival data by month.<BR>Every element is sorted by FsItem.date
 * @author minjdai
 *
 */
public class FsMonth extends TreeSet<FsItem> implements Jsonable {

	private static final long serialVersionUID = 720899135133138540L;
	public FsMonth() {
		super(new Comparator<FsItem>() {
			@Override
			public int compare(FsItem o1, FsItem o2) {

				if (o1 != null && o2 != null
						&& o1.getDateNumber() > o2.getDateNumber()) {
					return 1;
				} else if (o1 != null && o2 != null
						&& o1.getDateNumber() < o2.getDateNumber()) {
					return -1;
				}

				return 0;
			}
		});
	}
	@Override
	public String stringify() {
		return gson.toJson(this);
	}

	
	private transient Gson gson = new GsonBuilder().setPrettyPrinting().create();
}
