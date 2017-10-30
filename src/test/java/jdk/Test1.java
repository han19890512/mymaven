package jdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class Test1 {
	
	@Test
	public void test1(){
		Map<String,String> map=new HashMap<String,String>();
		
		Set<Map.Entry<String,String>> en=map.entrySet();
		
		Iterator<Map.Entry<String,String>> i=en.iterator();
		while(i.hasNext()){
			Map.Entry<String,String>  m=i.next();
			m.getKey();
			m.getValue();
		}
	}
}
