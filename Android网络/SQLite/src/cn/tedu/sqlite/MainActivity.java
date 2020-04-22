package cn.tedu.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 打开或者创建数据库
		SQLiteDatabase db = openOrCreateDatabase("tedu.db", MODE_PRIVATE, null);

		// 创建数据库
		// createTable(db);

		// 增加数据
		// insert(db);
		// newInsert(db);

		// 删除数据
		// delete(db);
		// newDelete(db);

		// 修改数据
		// update(db);
		// newUpdate(db);
		
		// 查询数据
		newQuery(db);
	}

	private void newQuery(SQLiteDatabase db) {
		String table = "students";
		String[] columns = { "age", "gender", "id", "name" };
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor c = db.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy);
		// --- 遍历方式1 ---
		// while(c.moveToNext()) {
		//
		// }
		// --- 遍历方式2 ---
		// if(c.moveToFirst()) {
		// do {
		//
		// } while(c.moveToNext());
		// }
		// --- 遍历方式3 ---
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Log.d("tedu", "---------------");
			Log.d("tedu", "id=" + c.getInt(c.getColumnIndex("id")));
			Log.d("tedu", "name=" + c.getString(c.getColumnIndex("name")));
			Log.d("tedu", "age=" + c.getInt(c.getColumnIndex("age")));
			Log.d("tedu", "gender=" + c.getInt(c.getColumnIndex("gender")));
		}
		if(!c.isClosed()) {
			c.close();
		}
	}
	
	private void newUpdate(SQLiteDatabase db) {
		String sql = "update students " + "set age=20 " + "where id=3 or id=5";
		db.execSQL(sql);
		db.close();
	}
	
	private void newDelete(SQLiteDatabase db) {
		String name = "Jason";
		String table = "students";
		String whereClause = "name=?";
		String[] whereArgs = { name };
		int affectedRows = db.delete(table, whereClause, whereArgs);
		Log.d("tedu", "affectedRows = " + affectedRows);
		db.close();
	}

	public void newInsert(SQLiteDatabase db) {
		String table = "students";
		String nullColumnHack = "id";
		ContentValues values = new ContentValues();
		values.put("name", "Mike");
		values.put("age", 22);
		values.put("gender", 1);
		long id = db.insert(table, nullColumnHack, values);
		Log.d("tedu", "id=" + id);
		db.close();
	}

	public void update(SQLiteDatabase db) {
		String sql = "update students " + 
	            "set age=20 " + 
				"where id=3 or id=5";
		db.execSQL(sql);
		db.close();
	}

	public void delete(SQLiteDatabase db) {
		String sql = "delete from students " +
				"where id=4";
		// sql = "delete from students";
		db.execSQL(sql);
		db.close();
	}

	public void insert(SQLiteDatabase db) {
		String sql = "insert into students " + 
				"(name, age, gender) " +
				"values " + 
				"('Lily', 18, 0)";
		db.execSQL(sql);

		sql = "insert into students " + 
				"(name, age, gender) " +
				"values " + 
				"('Rose', 19, 0)";
		db.execSQL(sql);

		sql = "insert into students " + 
				"(name, age, gender) " +
				"values " + 
				"('Billy', 22, 1)";
		db.execSQL(sql);

		sql = "insert into students " + 
				"(name, age, gender) " +
				"values " + 
				"('Alex', 20, 1)";
		db.execSQL(sql);

		sql = "insert into students " + 
				"(name, age, gender) " +
				"values " + 
				"('Jason', 21, 1)";
		db.execSQL(sql);
		db.close();
	}


	public void createTable(SQLiteDatabase db) {
		// 创建数据库
		String sql = "create table students (" +
				"id integer primary key autoincrement," +
				"name varchar(16) not null unique, " +
				"age integer, " + 
				"gender integer" + 
				");";
		db.execSQL(sql);
		db.close();
	}
}
