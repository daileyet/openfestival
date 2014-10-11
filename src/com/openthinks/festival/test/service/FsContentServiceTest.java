package com.openthinks.festival.test.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import sql.exception.TransactionException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.festival.data.model.FsContents;
import com.openthinks.festival.data.model.FsItem;
import com.openthinks.festival.data.model.FsMonth;
import com.openthinks.festival.web.service.FsContentService;

public class FsContentServiceTest extends FsDaoTest {

	FsContentService service = WebContexts.get().lookup(FsContentService.class);

	public void testGetAllItems() {
		List<FsItem> list = service.getAllContent();
		if (list.isEmpty()) {
			System.out.println("No Data");
		} else
			for (FsItem entity : list) {
				System.out.println(entity);
			}
	}

	public void testRemoveItem() throws TransactionException {
		List<FsItem> list = service.getAllContent();
		for(FsItem item:list){
			service.removeContent(item);
			//break;
		}
	}

	public void testAddItems() throws JsonSyntaxException, JsonIOException, FileNotFoundException, TransactionException{
		//R:\MyGit\openfestival\WebContent\data\fs-content.json
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FsContents content=gson.fromJson(new FileReader("R:\\MyGit\\openfestival\\WebContent\\data\\fs-content.json"), FsContents.class);
		content.rebuild();
		
		List<FsMonth> fsMonths=content.months();
		
		for(FsMonth month:fsMonths){
			for(FsItem item:month){
				service.addContent(item);
			}
		}
//		
		System.out.println(content.stringify());
		
	}
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException, TransactionException {
		FsContentServiceTest tester = new FsContentServiceTest();
//		tester.testGetAllItems();
//
//		tester.testRemoveItem();
//
		tester.testGetAllItems();
		
//		tester.testAddItems();
	}

}
