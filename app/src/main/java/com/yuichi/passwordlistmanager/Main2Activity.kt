package com.yuichi.passwordlistmanager

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

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

        var s:String
        cur?.use {c->
            while (c.moveToNext()){
                //val key = c.getInt(c.getColumnIndex("_id"))
                val name = c.getString(c.getColumnIndex("Name"))
                s=name
                val url = c.getString(c.getColumnIndex("URL"))
                val id = c.getString(c.getColumnIndex("ID"))
                val password = c.getString(c.getColumnIndex("Password"))

                if(name.equals(key)){
                    findViewById<EditText>(R.id.txtID2).setText(id)
                    findViewById<TextView>(R.id.txtName2).setText(name)
                    findViewById<EditText>(R.id.txtURL).setText(url)
                    findViewById<EditText>(R.id.txtPassword2).setText(password)
                }
            }
        }


        //更新ボタン
        findViewById<Button>(R.id.btnUpDateGo) .setOnClickListener{

            /*val intent = Intent(this,RecordUpdate::class.java)
            startActivityForResult(intent,1)*/

            //Toast.makeText(this,intent.getStringExtra("key"), Toast.LENGTH_SHORT).show()
            val name =findViewById<TextView>(R.id.txtName2).text.toString()
            val id =findViewById<EditText>(R.id.txtID2).text.toString()
            val password =findViewById<EditText>(R.id.txtPassword2).text.toString()
            val url =findViewById<EditText>(R.id.txtURL).text.toString()


            val update = ContentValues().apply {
                put("Name",name)
                put("ID",id)
                put("Password",password)
                put("URL",url)
            }

            dbw?.update("IDandPass",update,"Name = ?", arrayOf(findViewById<TextView>(R.id.txtName2).text.toString()))
            Toast.makeText(this,"更新されました",Toast.LENGTH_SHORT).show()

        }


        //削除ボタン
        findViewById<Button>(R.id.btnDerete).setOnClickListener {
            //val edittext = EditText(this)
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("削除しますか？")
            dialog.setPositiveButton("OK",DialogInterface.OnClickListener{_,_->

                db?.delete("IDandPass","Name = ?", arrayOf(findViewById<TextView>(R.id.txtName2).text.toString()))

                Toast.makeText(this,"1件削除されました",Toast.LENGTH_SHORT).show()

                setResult(1)
                finish()

            })
            dialog.setNegativeButton("キャンセル",null)
            dialog.show()
        }


        //外部のWebサイトへ
        findViewById<Button>(R.id.btnLink).setOnClickListener {
            val url = Uri.parse(findViewById<TextView>(R.id.txtURL).text.toString())
            val intent = Intent(Intent.ACTION_VIEW,url)
            try{
                startActivity(intent)
            }catch (e:Exception){
                Toast.makeText(this,"たぶんURLが違います",Toast.LENGTH_SHORT).show()
            }

        }
    }
}
