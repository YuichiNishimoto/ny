package com.yuichi.passwordlistmanager

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MyFragment:Fragment(){

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_layout,container,false)

        view.findViewById<Button>(R.id.btnTuikaTouroku).setOnClickListener {
            val intent = Intent(context,UseSQLite::class.java)
            startActivityForResult(intent,1)
            onActivityResult(1,1,intent)
        }

        view.findViewById<Button>(R.id.btnZenkenSakuzyo).setOnClickListener {

            val dialog = AlertDialog.Builder(context!!)
            dialog.setTitle("全て削除しますか？")
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // OKボタン押したときの処理
                val dbforderete = MyDatabaseOpenHelper(context).writableDatabase
                dbforderete.execSQL("DELETE FROM IDandPass")
                dbforderete.close()
                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
            })
            dialog.setNegativeButton("キャンセル", null)
            dialog.show()


        }

        view.findViewById<Button>(R.id.btnSettei).setOnClickListener {
            startActivity(Intent(context,Option::class.java))
        }
        return view
    }


}



