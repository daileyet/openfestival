package com.openthinks.festival.web.controller;

import utilities.Checker;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.utils.json.OperationJson;
import com.openthinks.festival.data.model.entity.FsUserEntity;
import com.openthinks.festival.web.service.FsUserService;

import exception.CheckerNoPassException;

@Controller("/api/users")
public class FsUsersController {

	FsUserService service=WebContexts.get().lookup(FsUserService.class);
	
	@Mapping("/add")
	@ResponseReturn(contentType="text/json")
	public String createUser(WebAttributers was){
		String name=(String) was.get("name");
		String password=(String)was.get("password");
		String password2=(String)was.get("password2");
		String email=(String)was.get("email");
		
		try{
			Checker.require(name).notNull();
			Checker.require(password).notNull();
			Checker.require(password).equalTo(password2);
			Checker.require(email).notNull();
		}catch(CheckerNoPassException e){
			return OperationJson.build().error(e.toString()).toString();
		}
		
		FsUserEntity entity=new FsUserEntity();
		entity.setName(name);
		entity.setPassword(password);
		entity.setEmail(email);
		entity.lock();
		
		service.addUser(entity);
		
		return OperationJson.build().sucess("User, "+name+" was added successfully!").toString();
	}
	
}
