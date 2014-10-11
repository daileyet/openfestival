/**
 * 
 */
package com.openthinks.festival.web.controller;

import java.util.ArrayList;
import java.util.List;

import sql.exception.TransactionException;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.utils.json.OperationJson;
import com.openthinks.festival.data.model.FsContents;
import com.openthinks.festival.data.model.FsImage;
import com.openthinks.festival.data.model.FsItem;
import com.openthinks.festival.web.service.FsContentService;

/**
 * @author minjdai
 *
 */
@Controller("/api/contents")
public class FsContentsController {

	FsContentService contentService=WebContexts.get().lookup(FsContentService.class);
	
	@Mapping("/add")
	@ResponseReturn(contentType = "text/json")
	public String addFestival(WebAttributers was) throws TransactionException{
		String name=(String) was.get("name");
		String month=(String) was.get("mon");
		String date=(String) was.get("date");
		String desc=(String) was.get("desc");
		List<FsImage> fsImages=getFsImages(was);
		
		FsItem item=new FsItem();
		item.setName(name);
		item.setDate(date);
		item.setDesc(desc);
		item.setMonth(month);
		item.setImages(fsImages);
		contentService.addContent(item);
		
		
		return OperationJson.build().sucess("Festival, "+name+" at "+month+","+date+" was added successfully!").toString();
	}
	
	@Mapping("/get")
	@ResponseReturn(contentType="text/json")
	public String getFestival(WebAttributers was){
		List<FsItem> items=contentService.getAllContent();
		FsContents contents=FsContents.valueOf(items);
		return contents.stringify();
	}

	private List<FsImage> getFsImages(WebAttributers was) {
		int index=0;
		String paramerCaption,paramerUrl;
		List<FsImage> images=new ArrayList<>();
		while(true){
			paramerCaption="images["+index+"][caption]";
			paramerUrl="images["+index+"][url]";
			FsImage image=new FsImage((String)was.get(paramerUrl), (String)was.get(paramerCaption));
			if(image.isValid()){
				images.add(image);
				index++;
				continue;
			}
			break;
		}
		return images;
	}
	
}
