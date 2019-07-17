package com.yuichi.passwordlistmanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseOpenHelper(var context: Context?) :SQLiteOpenHelper(context,"UserDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IDandPass(" +
                "Name TEXT PRIMARY KEY," +
                "URL TEXT," +
                "ID TEXT," +
                "Password TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS IDandPass;")
        onCreate(db)
    }


}