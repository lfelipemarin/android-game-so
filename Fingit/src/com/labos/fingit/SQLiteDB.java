package com.labos.fingit;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class SQLiteDB extends Activity {

	SQLiteDatabase scores;
	String DBName;

	public SQLiteDB(SQLiteDatabase scores, String DBName) {
		this.scores = scores;
		this.DBName = DBName;
	}

	// CREATE TABLE IF NOT EXISTS
	public void createTable(String table) {
		try {
			scores = openOrCreateDatabase(DBName, Context.MODE_PRIVATE, null);
			scores.execSQL("CREATE TABLE IF  NOT EXISTS "
					+ table
					+ " (HASH VARCHAR(500), PUNTOS INT(11), FECHA TIMESTAMP, PRIMARY KEY(HASH, FECHA));");
			scores.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error in creating table",
					Toast.LENGTH_LONG);
		}
	}

	// THIS FUNCTION INSERTS DATA TO THE DATABASE
	public void insertIntoTable(String table, String value1, String value2,
			String value3) {
		try {
			scores = openOrCreateDatabase(DBName, Context.MODE_PRIVATE, null);
			scores.execSQL("INSERT INTO " + table
					+ "(HASH, PUNTOS, FECHA) VALUES('" + value1 + "','"
					+ value2 + "','" + value3 + "')");
			scores.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error in inserting into table", Toast.LENGTH_LONG);
		}
	}

	// THIS FUNCTION SHOWS DATA FROM THE DATABASE
	/*
	 * public void showTableValues() { try { mydb = openOrCreateDatabase(DBNAME,
	 * Context.MODE_PRIVATE, null); Cursor allrows =
	 * mydb.rawQuery("SELECT * FROM " + TABLE, null);
	 * System.out.println("COUNT : " + allrows.getCount()); Integer cindex =
	 * allrows.getColumnIndex("NAME"); Integer cindex1 =
	 * allrows.getColumnIndex("PLACE");
	 * 
	 * TextView t = new TextView(this);
	 * t.setText("========================================"); //
	 * Linear.removeAllViews(); Linear.addView(t);
	 * 
	 * if (allrows.moveToFirst()) { do { LinearLayout id_row = new
	 * LinearLayout(this); LinearLayout name_row = new LinearLayout(this);
	 * LinearLayout place_row = new LinearLayout(this);
	 * 
	 * final TextView id_ = new TextView(this); final TextView name_ = new
	 * TextView(this); final TextView place_ = new TextView(this); final
	 * TextView sep = new TextView(this);
	 * 
	 * String ID = allrows.getString(0); String NAME = allrows.getString(1);
	 * String PLACE = allrows.getString(2);
	 * 
	 * id_.setTextColor(Color.RED); id_.setPadding(20, 5, 0, 5);
	 * name_.setTextColor(Color.RED); name_.setPadding(20, 5, 0, 5);
	 * place_.setTextColor(Color.RED); place_.setPadding(20, 5, 0, 5);
	 * 
	 * System.out.println("NAME " + allrows.getString(cindex) + " PLACE : " +
	 * allrows.getString(cindex1)); System.out.println("ID : " + ID +
	 * " || NAME " + NAME + "|| PLACE : " + PLACE);
	 * 
	 * id_.setText("ID : " + ID); id_row.addView(id_); Linear.addView(id_row);
	 * name_.setText("NAME : " + NAME); name_row.addView(name_);
	 * Linear.addView(name_row); place_.setText("PLACE : " + PLACE);
	 * place_row.addView(place_); Linear.addView(place_row);
	 * sep.setText("---------------------------------------------------------------"
	 * ); Linear.addView(sep); } while (allrows.moveToNext()); } mydb.close(); }
	 * catch (Exception e) { Toast.makeText(getApplicationContext(),
	 * "Error encountered.", Toast.LENGTH_LONG); } }
	 */
	// THIS FUNCTION UPDATES THE DATABASE ACCORDING TO THE CONDITION
	public void updateTable(String table, String campo, String value) {
		try {
			scores = openOrCreateDatabase(DBName, Context.MODE_PRIVATE, null);
			scores.execSQL("UPDATE " + table + " SET NAME = '" + value
					+ "' WHERE PLACE = '" + campo + "'");
			scores.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered",
					Toast.LENGTH_LONG);
		}
	}

	// THIS FUNCTION DELETES VALUES FROM THE DATABASE ACCORDING TO THE CONDITION
	public void deleteValues(String table, String campo) {
		try {
			scores = openOrCreateDatabase(DBName, Context.MODE_PRIVATE, null);
			scores.execSQL("DELETE FROM " + table + " WHERE PLACE = '" + campo
					+ "'");
			scores.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error encountered while deleting.", Toast.LENGTH_LONG);
		}
	}

	// THIS FUNTION DROPS A TABLE
	public void dropTable(String table) {
		try {
			scores = openOrCreateDatabase(DBName, Context.MODE_PRIVATE, null);
			scores.execSQL("DROP TABLE " + table);
			scores.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error encountered while dropping.", Toast.LENGTH_LONG);
		}
	}

}
