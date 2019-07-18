package com.yuichi.passwordlistmanager

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

data class LoginPass(val pswd:String?)

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //SharedPrefarencesオブジェクトを取得
        val pref = getSharedPreferences("masterpass", Context.MODE_PRIVATE)
        //masterpassファイルのloginとセットの値を取得,無ければnull
        var p1 = LoginPass(pref?.getString("login-3",null))

        //入力された値を取得


        //パスワードが存在するかの判定、nullならパスワードが存在しないので
        //新規登録
        if((pref?.getString("login-3",null) != null)){

        }else{  //パスワードが存在しなければ登録する
                    //Toast.makeText(this,"パスワードが存在しません",Toast.LENGTH_SHORT).show()
            val myedit = EditText(this)
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("文字を入力してください")
            dialog.setView(myedit)
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // OKボタン押したときの処理
                val userText = myedit.getText().toString()
                pref.edit().putString("login-3",userText).apply()
                p1=LoginPass(pref?.getString("login-3",null))

                Toast.makeText(this, "$userText と入力されました", Toast.LENGTH_SHORT).show()
            })
            dialog.setNegativeButton("キャンセル", null)
            dialog.show()
        }

        val btn = findViewById<Button>(R.id.btnLogin)
        btn.setOnClickListener {
            val nyuuryoku = findViewById<EditText>(R.id.txtpswddd).text.toString()
            val p2 = LoginPass(nyuuryoku)


            if( p1.pswd == p2.pswd.toString() ){
                setResult(1)
                finish()
            }else{
                Toast.makeText(this,nyuuryoku.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}
