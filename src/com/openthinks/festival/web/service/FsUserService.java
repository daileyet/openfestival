package com.openthinks.festival.web.service;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;

import com.openthinks.festival.data.model.entity.FsUserEntity;

public class FsUserService {

	public void addUser(FsUserEntity entity) {
		Session session = SessionFactory.getSession();

		session.save(entity);

		session.close();
	}

	public void unlockUser(String fuid) {

		Session session = SessionFactory.getSession();

		session.update("UPDATE fsusers SET islock=? WHERE fuid=?", new String[]{"N",fuid});

		session.close();

	}
	
	
	public void lockUser(String fuid) {

		Session session = SessionFactory.getSession();

		session.update("UPDATE fsusers SET islock=? WHERE fuid=?", new String[]{"Y",fuid});

		session.close();

	}

}
