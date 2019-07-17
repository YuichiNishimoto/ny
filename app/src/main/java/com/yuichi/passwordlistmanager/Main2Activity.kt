package com.yuichi.passwordlistmanager

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val key=intent.getStringExtra("key")

        val dbforread=MyDatabaseOpenHelper(this)
        val db:SQLiteDatabase?=dbforread.readableDatabase

        val dbw:SQLiteDatabase?=dbforread.writableDatabase

        val sql = "select * from IDandPass"
        val cur = db?.rawQuery(sql,null)
        cur?.use {c->
            while (c.moveToNext()){
                //val key = c.getInt(c.getColumnIndex("_id"))
                val name = c.getString(c.getColumnIndex("Name"))
                val url = c.getString(c.getColumnIndex("URL"))
                val id = c.getString(c.getColumnIndex("ID"))
                val password = c.getString(c.getColumnIndex("Password"))

                if(name.equals(key)){
                    findViewById<TextView>(R.id.txtID2).text=id
                    findViewById<TextView>(R.id.txtName2).text=name
                    findViewById<TextView>(R.id.txtURL).text=url
                    findViewById<TextView>(R.id.txtPassword2).text=password
                }

            }

        }

        findViewById<Button>(R.id.btnDetaile) .setOnClickListener{

            Toast.makeText(this,intent.getStringExtra("key"), Toast.LENGTH_SHORT).show()
            val txt =findViewById<EditText>(R.id.edtHenkou).text.toString()
            val update = ContentValues().apply {
                put("Name",txt)
            }

            dbw?.update("IDandPass",update,"Name = ?", arrayOf(findViewById<TextView>(R.id.txtName2).text.toString()))

        }
    }
}
