/**
 * 
 */
package com.openthinks.festival.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.openthinks.festival.data.model.FsContents;
import com.openthinks.festival.data.model.FsImage;
import com.openthinks.festival.data.model.FsItem;
import com.openthinks.festival.data.model.FsMonthType;
/**
 * @author minjdai
 * 
 */
public class FsContentsTest {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {

		testGenerateJson();
		testGenerateObject();

	}

	private static void testGenerateObject() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FsContents content=gson.fromJson(new FileReader("R:\\MySvn\\openfestival\\src\\com\\openthinks\\festival\\test\\fs-content.json"), FsContents.class);
		
		content.rebuild();
		FsItem item = new FsItem();
		item.setName("test");
		item.setDate("2");
		item.setDesc("test");
		content.add(FsMonthType.Jan, item);
		
		String striyfy = gson.toJson(content);

		System.out.println(striyfy);
	}

	private static void testGenerateJson() {
		FsImage image = new FsImage();
		image.setCaption("谷雨1");
		image.setUrl("http://www.bitauto.com/hot/jieqi/allimg/guyu/5.png");

		FsItem item = new FsItem();
		item.setName("清明");
		item.setDate("4");
		item.setDesc("谷雨是二十四节气的第六个节气，每年4月19日～21日时太阳到达黄经30°时为谷雨，源自古人“雨生百谷”之说。");
		item.add(image);

		image = new FsImage();
		image.setCaption("谷雨2");
		image.setUrl("http://photos.tuchong.com/350832/f/4959742.jpg");

		item.add(image);

		FsContents content = new FsContents();

		content.add(FsMonthType.Apr, item);

		Gson gson =  new GsonBuilder().setPrettyPrinting().create();

		String striyfy = gson.toJson(content);

		System.out.println(striyfy);
	}

}
