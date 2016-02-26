package cn.mstar.store.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 用于创建和更新数据库
 * @author duwenjun 2015-7-15
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
	
	private static DatabaseHelper mInstance;
	
	/**
	 * 构造函数
	 * 
	 * @param context
	 *            context数据
	 */
	private DatabaseHelper(Context context)
	{
		// 对象 ，数据库名 ，复杂查询 ，数据库版本
		super(context, DBFinals.DATABASE_NAME, null,
				DBFinals.DBTABASE_VERSION);
	}
	
	public synchronized static DatabaseHelper getInstance(Context context)
	{
		if (mInstance == null)
		{
			mInstance = new DatabaseHelper(context);
		}
		return mInstance;
	};
	
	/**
	 * 重写onCreate方法，建立数据库
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(DBFinals.DBSports.DATABASE_TABLE_HISTORY_SEARCH_CREATE);
	}
	
	/**
	 * 重写onUpgrade方法，根据版本号不同（DBTABASE_VERSION）更新数据库
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS "
				+ DBFinals.DBSports.BATABASE_TABLE_HISTORY_SEARCH);
		onCreate(db);
	}
	
}
