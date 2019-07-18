package com.yuichi.passwordlistmanager

import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
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


        //更新ボタン
        findViewById<Button>(R.id.btnUpDateGo) .setOnClickListener{

            Toast.makeText(this,intent.getStringExtra("key"), Toast.LENGTH_SHORT).show()
            val txt =findViewById<EditText>(R.id.edtHenkou).text.toString()
            val update = ContentValues().apply {
                put("Name",txt)
            }

            dbw?.update("IDandPass",update,"Name = ?", arrayOf(findViewById<TextView>(R.id.txtName2).text.toString()))

        }


        //削除ボタン
        findViewById<Button>(R.id.btnDerete).setOnClickListener {
            val edittext = EditText(this)
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("削除しますか？")
            dialog.setPositiveButton("OK",DialogInterface.OnClickListener{_,_->

                //削除動作（後で書く）

                Toast.makeText(this,"1件削除されました",Toast.LENGTH_SHORT).show()

                setResult(1)
                finish()

            })
            dialog.setNegativeButton("キャンセル",null)
            dialog.show()
        }
    }
}
