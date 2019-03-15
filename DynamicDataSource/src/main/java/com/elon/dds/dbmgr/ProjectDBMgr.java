package com.elon.dds.dbmgr;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目数据库管理。提供根据项目编码查询数据库名称和IP的接口。
 * @author elon
 * @version 2018年2月25日
 */
public class ProjectDBMgr {
	
	/**
	 * 保存项目编码与数据名称的映射关系。这里是硬编码，实际开发中这个关系数据可以保存到redis缓存中；
	 * 新增一个项目或者删除一个项目只需要更新缓存。到时这个类的接口只需要修改为从缓存拿数据。
	 */
	private Map<String, String> dbNameMap = new HashMap<String, String>();
	
	/**
	 * 保存项目编码与数据库IP的映射关系。
	 */
	private Map<String, String> dbIPMap = new HashMap<String, String>();
	
	private ProjectDBMgr() {
		dbNameMap.put("project_001", "db_project_001");
		dbNameMap.put("project_002", "db_project_002");
		dbNameMap.put("project_003", "db_project_003");
		
		dbIPMap.put("project_001", "127.0.0.1");
		dbIPMap.put("project_002", "127.0.0.1");
		dbIPMap.put("project_003", "127.0.0.1");
	}
	
	public static ProjectDBMgr instance() {
		return ProjectDBMgrBuilder.instance;
	}
	
	// 实际开发中改为从缓存获取
	public String getDBName(String projectCode) {
		if (dbNameMap.containsKey(projectCode)) {
			return dbNameMap.get(projectCode);
		}
		
		return "";
	}
	
	//实际开发中改为从缓存中获取
	public String getDBIP(String projectCode) {
		if (dbIPMap.containsKey(projectCode)) {
			return dbIPMap.get(projectCode);
		}
		
		return "";
	}
	
	private static class ProjectDBMgrBuilder {
		private static ProjectDBMgr instance = new ProjectDBMgr();
	}
}
