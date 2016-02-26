package cn.mstar.store.db;

/**
 * 数据库常量类
 * 
 * @author duwenjun 2015-7-15
 * 
 */
public class DBFinals
{
	public static final String DATABASE_NAME = "mstar";// 数据库名
	public static final int DBTABASE_VERSION = 1;// 数据库版本
	
	public static class DBSports
	{
	
	
		public static final String BATABASE_TABLE_HISTORY_SEARCH="history_search";//历史搜索表名
	
		//建表语句--历史搜索记录
		public static final String DATABASE_TABLE_HISTORY_SEARCH_CREATE=" CREATE TABLE IF NOT EXISTS "
				+BATABASE_TABLE_HISTORY_SEARCH
				+"("
				+"id INTEGER PRIMARY KEY AUTOINCREMENT,"  //id
				+"text text" //历史搜索记录的文字
				+")";
	}

	// i only need to save json object inside the db and retrieve them when needed.

}
