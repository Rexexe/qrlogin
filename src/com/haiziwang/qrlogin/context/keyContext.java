package com.haiziwang.qrlogin.context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class keyContext {
	public static Map<String, Thread> ks = new ConcurrentHashMap<String, Thread>();
	public static void registerSub(String key){
		ks.put(key,Thread.currentThread());
		System.out.println("registerSub  key="+key+" keyCount:"+ks.keySet().size());
	}
	
	public static void releaseSub(String key){
		ks.remove(key);
		System.out.println("releaseSub  key="+key+" keyCount:"+ks.keySet().size());
	}
	
	public static void pubToClient(String key){
		if(ks.containsKey(key)){
			System.out.println("ks contain key"+ks.containsKey(key));
			ks.get(key).interrupt();
		}
	}
	
	public static String ksDetail(){
		StringBuilder sbd = new StringBuilder();
		Set<String> keySet = ks.keySet();
		int c = ks.size();
		sbd.append("total:"+c+"<br/>");
		sbd.append("——————————————————————————————————<br/>");
		for(String key:keySet){
			sbd.append("key:"+key+" <br/>");
		}
		sbd.append("——————————————————————————————————<br/>");
		return sbd.toString();
	}
}
