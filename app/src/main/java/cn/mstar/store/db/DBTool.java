package cn.mstar.store.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;

/**
 * @name 数据库相关操作类
 * @author duwenjun 2015-7-15
 * 
 * @edit_remark 优化代码
 */
public class DBTool
{
	private Context mCtx = null;
	private DatabaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	
	/**
	 * 构造函数 1
	 * 
	 * @param ctx
	 *            context对象
	 */
	public DBTool(Context ctx)
	{
		this.mCtx = ctx;
	}
	
	/**
	 * 打开数据连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public DBTool open() throws SQLException
	{
		dbHelper = DatabaseHelper.getInstance(mCtx);
		db = dbHelper.getWritableDatabase(); // 如果数据库不存在就建立一个，反之存在则根据版本更新
		return this;
	}
	
	/**
	 * 列表查询用 1
	 * 
	 * @param tableName
	 *            表名
	 * @param strCols
	 *            列名
	 * @param strWhere
	 *            条件
	 * @param strGroupby
	 *            分组
	 * @param strOrderby
	 *            排序
	 * @return 数据指针
	 */
	public Cursor getAll(String tableName, String[] strCols, String strWhere,
			String strGroupby, String strOrderby)
	{
		if (!db.isOpen())
		{
			db = dbHelper.getWritableDatabase();
		}
		Cursor cursor = db.query(tableName, // 表名
				strCols, // 列
				strWhere, // where语句
				null, // where语句的参数
				strGroupby, // GROUP by语句
				null, // HAVING语句
				strOrderby // order by语句
				);
		return cursor;
	}
	
	/**
	 * 列表查询用 2
	 * 
	 * @param sql
	 *            传入sql语句直接查询
	 * @return 数据指针
	 */
	public Cursor getAll(String sql)
	{
		if (!db.isOpen())
		{
			db = dbHelper.getWritableDatabase();
		}
		Cursor cursor = null;
		synchronized (dbHelper)
		{
			cursor = db.rawQuery(sql, null);
		}
		return cursor;
	}
	
	/**
	 * 单个数据查询 1
	 * 
	 * @param rowId
	 *            数据id
	 * @param tableName
	 *            表名
	 * @param key
	 *            关键字
	 * @param strCols
	 *            列名
	 * @param strWhere
	 *            条件
	 * @param strGroupby
	 *            分组
	 * @param strOrderby
	 *            排序
	 * @return 单个数据指针
	 */
	public Cursor get(long rowId, String tableName, String key,
			String[] strCols, String strWhere, String strGroupby,
			String strOrderby)
	{
		if (!db.isOpen())
		{
			db = dbHelper.getWritableDatabase();
		}
		Cursor cursor = null;
		synchronized (dbHelper)
		{
			cursor = db.query(tableName, strCols, key + "=" + rowId, null,
					strGroupby, null, strOrderby);
			// 如果指针存在，就把指针移到第一个条目上
			if (cursor != null)
			{
				cursor.moveToFirst();
			}
		}
		return cursor;
	}
	
	/**
	 * 单个数据查询 2
	 * 
	 * @param sql
	 *            传入sql语句直接查询
	 * @return 单个数据指针
	 */
	public Cursor get(String sql)
	{
		if (!db.isOpen())
		{
			db = dbHelper.getWritableDatabase();
		}
		Cursor cursor = null;
		synchronized (dbHelper)
		{
			cursor = db.rawQuery(sql, null);
			// 如果指针存在，就把指针移到第一个条目上
			if (cursor != null)
			{
				cursor.moveToFirst();
			}
		}
		return cursor;
	}
	
	/**
	 * 更新数据
	 * 
	 * @param rowId
	 *            数据id
	 * @param tableName
	 *            表名
	 * @param key
	 *            更新列名
	 * @param args
	 *            更新数据
	 * @return 是否更新成功
	 */
	public boolean update(long rowId, String tableName, String key,
			ContentValues args)
	{
		boolean id = false;
		synchronized (dbHelper)
		{
			if (!db.isOpen())
			{
				db = dbHelper.getWritableDatabase();
			}
			db.beginTransaction();
			try
			{
				id = db.update(tableName, args, key + "=" + rowId, null) > 0;
				db.setTransactionSuccessful(); // 设置事务成功完成
			} catch (SQLException e)
			{
				return id;
			} catch (Exception e)
			{
				return id;
			} finally
			{
				db.endTransaction();
			}
		}
		return id;
	}
	
	/**
	 * 更新数据
	 * 
	 * @param tableName
	 * @param args
	 * @return
	 */
	public boolean update(String tableName, ContentValues args)
	{
		boolean id = false;
		synchronized (dbHelper)
		{
			if (!db.isOpen())
			{
				db = dbHelper.getWritableDatabase();
			}
			db.beginTransaction();
			try
			{
				id = db.update(tableName, args, null, null) > 0;
				db.setTransactionSuccessful(); // 设置事务成功完成
			} catch (SQLException e)
			{
				return id;
			} catch (Exception e)
			{
				return id;
			} finally
			{
				db.endTransaction();
			}
		}
		return id;
	}
	
	/**
	 * 插入新数据
	 * 
	 * @param tableName
	 *            表名
	 * @param args
	 *            数据
	 * @return 成功返回id，失败返回-1
	 */
	public long insert(String tableName, ContentValues args)
	{
		long id = -1;
		synchronized (dbHelper)
		{
			// 看数据库是否关闭
			if (!db.isOpen())
			{
				db = dbHelper.getWritableDatabase();
			}
			// 开始事务
			db.beginTransaction();
			try
			{
				// 表名 ，不重要，内容值
				id = db.insert(tableName, null, args);
				db.setTransactionSuccessful(); // 设置事务成功完成
			} catch (SQLException e)
			{
				return id;
			} catch (Exception e)
			{
				return id;
			} finally
			{
				db.endTransaction();
			}
		}
		return id;
	}
	
	/**
	 * 删除数据
	 * 
	 * @param rowId
	 *            数据id
	 * @param tableName
	 *            表名
	 * @param key
	 *            关键字
	 * @return 是否删除成功
	 */
	public boolean delete(long rowId, String tableName, String key)
	{
		boolean id = false;
		synchronized (dbHelper)
		{
			// 看数据库是否关闭
			if (!db.isOpen())
			{
				db = dbHelper.getWritableDatabase();
			}
			// 开始事务
			db.beginTransaction();
			try
			{
				// 表名 ，不重要，内容值
				id = db.delete(tableName, key + "=" + rowId, null) > 0;
				db.setTransactionSuccessful(); // 设置事务成功完成
			} catch (SQLException e)
			{
				return id;
			} catch (Exception e)
			{
				return id;
			} finally
			{
				db.endTransaction();
			}
		}
		return id;
	}
	//通用执行sql语句
	public void executeSQL(String sql){
		if (!db.isOpen())
		{
			db = dbHelper.getWritableDatabase();
		}
		
		synchronized (dbHelper)
		{
			db.execSQL(sql);
		
		}
	}
	
	/**
	 * 清空数据
	 * 
	 * @param tableName表名
	 * @return
	 */
	public boolean delete(String tableName)
	{
		boolean id = false;
		synchronized (dbHelper)
		{
			// 看数据库是否关闭
			if (!db.isOpen())
			{
				db = dbHelper.getWritableDatabase();
			}
			// 开始事务
			db.beginTransaction();
			try
			{
				// 表名 ，不重要，内容值
				id = db.delete(tableName, null, null) > 0;
				db.setTransactionSuccessful(); // 设置事务成功完成
			} catch (SQLException e)
			{
				return id;
			} catch (Exception e)
			{
				return id;
			} finally
			{
				db.endTransaction();
			}
		}
		return id;
	}
	/**
	 * 关闭数据库
	 */
	public void close(){
		if(null != db && db.isOpen())
		{
			db.close();
		}
	}
	
}