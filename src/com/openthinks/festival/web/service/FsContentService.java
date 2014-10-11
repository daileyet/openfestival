package com.openthinks.festival.web.service;

import java.util.ArrayList;
import java.util.List;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.exception.TransactionException;

import com.openthinks.festival.data.model.FsImage;
import com.openthinks.festival.data.model.FsItem;
import com.openthinks.festival.data.model.entity.FsImageEntity;
import com.openthinks.festival.data.model.entity.FsItemEntity;

public class FsContentService {

	
	public void addContent(FsItem item) throws TransactionException{
		FsItemEntity entity=item.toEntity();
		Session session=SessionFactory.getSession();
		session.beginTransaction();
		session.disableAutoClose();
		session.save(entity);
		// persist images
		for(FsImage image:item.getImages()){
			FsImageEntity imgEntity=image.toEntity();
			session.save(imgEntity);
		}
		session.commit();
		session.enableAutoClose();
		session.close();
	}
	
	
	public List<FsItem> getAllContent(){
		Session session=SessionFactory.getSession();
		session.disableAutoClose();
		List<FsItemEntity> entityList=session.list(FsItemEntity.class);
		
		List<FsItem> itemList=new ArrayList<FsItem>();
		for(FsItemEntity entity :entityList ){
			//query images
			List<FsImageEntity> imgEntityList = session.list(FsImageEntity.class, "SELECT * FROM fsimages where fid=?", new String[]{entity.getFid()});
			FsItem item=FsItem.valueOf(entity);
			item.setEntityImages(imgEntityList);
			itemList.add(item);
		}
		session.enableAutoClose();
		session.close();
		return itemList;
	}
	
	
	public void removeContent(FsItem item) throws TransactionException{
		Session session=SessionFactory.getSession();
		session.beginTransaction();
		session.disableAutoClose();
		String fid=item.key();
		session.delete(item.toEntity());
		
		session.delete("DELETE FROM fsimages where fid=?", new String[]{fid});
		
		session.commit();
		session.enableAutoClose();
		session.close();
	}
	
	
}
