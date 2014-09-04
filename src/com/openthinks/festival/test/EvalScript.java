package com.openthinks.festival.test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalScript {
	private static LineNumberReader read;

	public static void main(String[] args) throws ScriptException, IOException {
		ScriptEngineManager factory=new ScriptEngineManager();
		ScriptEngine engine=factory.getEngineByName("JavaScript");
		engine.eval("var a={'Jan':'January'}");
		
		engine.eval("a['Feb']='February'");
		engine.eval("for(key in a)print(a[key]+'\\n')");
		StringBuffer buffer=new StringBuffer();
		String line=null;
		read = new LineNumberReader(new FileReader("R:\\MySvn\\openfestival\\src\\com\\openthinks\\festival\\scripts\\test\\fs-content.json"));
		do{
			line=read.readLine();
			buffer.append(line);
		}while(line!=null);
		
		engine.eval("var b="+buffer.toString());
	}
}
