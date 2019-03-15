package com.elon.dds.datasource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * 动态数据源管理器。
 * 
 * @author elon
 * @version 2018年2月25日
 */
public class DDSHolder {
	
	/**
	 * 管理动态数据源列表。<工程编码，数据源>
	 */
	private Map<String, DDSTimer> ddsMap = new HashMap<String, DDSTimer>();

	/**
	 * 通过定时任务周期性清除不使用的数据源
	 */
	private static Timer clearIdleTask = new Timer();
	static {
		clearIdleTask.schedule(new ClearIdleTimerTask(), 5000, 60 * 1000);
	};
	
	private DDSHolder() {
		
	}
	
	/*
	 * 获取单例对象
	 */
	public static DDSHolder instance() {
		return DDSHolderBuilder.instance;
	}
	
	/**
	 * 添加动态数据源。
	 * 
	 * @param projectCode 项目编码 
	 * @param dds dds
	 */
	public synchronized void addDDS(String projectCode, DataSource dds) {
		
		DDSTimer ddst = new DDSTimer(dds);
		ddsMap.put(projectCode, ddst);
	}
	
	/**
	 * 查询动态数据源
	 * 
	 * @param projectCode 项目编码
	 * @return dds
	 */
	public synchronized DataSource getDDS(String projectCode) {
		
		if (ddsMap.containsKey(projectCode)) {
			DDSTimer ddst = ddsMap.get(projectCode);
			ddst.refreshTime();
			return ddst.getDds();
		}
		
		return null;
	}
	
	/**
	 * 清除超时无人使用的数据源。
	 */
	public synchronized void clearIdleDDS() {
		
		Iterator<Entry<String, DDSTimer>> iter = ddsMap.entrySet().iterator();
		for (; iter.hasNext(); ) {
			
			Entry<String, DDSTimer> entry = iter.next();
			if (entry.getValue().checkAndClose())
			{
				iter.remove();
			}
		}
	}
	
	/**
	 * 单例构件类
	 * @author elon
	 * @version 2018年2月26日
	 */
	private static class DDSHolderBuilder {
		private static DDSHolder instance = new DDSHolder();
	}
}
