package com.yuichi.passwordlistmanager

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UseSQLite : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_sqlite)

        val dbhelper = MyDatabaseOpenHelper(this)
        val dbforwrite:SQLiteDatabase? = dbhelper.writableDatabase

        setResult(Activity.RESULT_CANCELED)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val values= ContentValues()

            Toast.makeText(this,"１件追加されました",Toast.LENGTH_SHORT).show()
            values.put("Name",findViewById<EditText>(R.id.edtName).text.toString())
            values.put("URL",findViewById<EditText>(R.id.edtURL).text.toString())
            values.put("ID",findViewById<EditText>(R.id.edtID).text.toString())
            values.put("Password",findViewById<EditText>(R.id.edtPassword).text.toString())

            //Toast.makeText(this,findViewById<TextView>(R.id.edtName).toString(),Toast.LENGTH_SHORT).show()

            dbforwrite?.insertOrThrow("IDandPass",null,values)

            findViewById<EditText>(R.id.edtName).editableText.clear()
            findViewById<EditText>(R.id.edtURL).editableText.clear()
            findViewById<EditText>(R.id.edtID).editableText.clear()
            findViewById<EditText>(R.id.edtPassword).editableText.clear()

            dbforwrite?.close()
        }
    }
}
