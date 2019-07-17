package com.yuichi.passwordlistmanager

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Option : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        val pref = getSharedPreferences("masterpass", Context.MODE_PRIVATE)

        findViewById<Button>(R.id.btnOption).setOnClickListener{
            pref.edit().putString("login-3",findViewById<EditText>(R.id.edtLoginPassOption).text.toString()).apply()
            findViewById<EditText>(R.id.edtLoginPassOption).text = null
            Toast.makeText(this,"パスワードが変更されました",Toast.LENGTH_SHORT).show()

        }

    }
}
