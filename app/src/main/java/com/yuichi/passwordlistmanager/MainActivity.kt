package com.yuichi.passwordlistmanager

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import java.net.URL

data class dataarray(val name:String,val url:String,val id:String,val password:String)

var flag = false  //ログイン用フラグ

class MainActivity : AppCompatActivity() {

    private var drawerToggle:ActionBarDrawerToggle? = null

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    private fun setupDrawer(drawer: DrawerLayout){
        val toggle = ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name)
        toggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(toggle)

        drawerToggle = toggle

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(drawerToggle?.onOptionsItemSelected(item) == true){
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun setViews(){
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        if(drawerLayout != null){
            setupDrawer(drawerLayout)
        }
    }

    override fun onRestart() {
        super.onRestart()
        reroad()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!flag){
            val intent = Intent(this,Login::class.java)
            startActivityForResult(intent,1)
            flag=true //次回のonCreateからはログイン不要
        }

        setViews()
        reroad()
    }

    fun reroad(){
        //リスト表示
        val listview = findViewById<ListView>(R.id.lstMain)


        //データベースから値を取得
        val dbhelper = MyDatabaseOpenHelper(this)
        val dbforread:SQLiteDatabase? = dbhelper.readableDatabase
        val cur = dbforread?.query("IDandPass", arrayOf("Name","URL","ID","Password"),
            null,null,null,null,null,null)

        val list = mutableListOf<dataarray>()
        var data:dataarray

        cur?.use { c->
            while (c.moveToNext()){
                val name = c.getString(c.getColumnIndex("Name"))
                val url = c.getString(c.getColumnIndex("URL"))
                val id = c.getString(c.getColumnIndex("ID"))
                val password = c.getString(c.getColumnIndex("Password"))

                data=dataarray(name,url,id,password)

                list.add(data)

                listview.adapter=MyAdapter(this,list)

            }
        }
        cur?.close()

        listview.setOnItemClickListener{parent,view,position,id->

            val item=listview.adapter.getItem(position) as dataarray

            val intent = Intent(this,Main2Activity::class.java)
            intent.putExtra("key",item.name)
            startActivityForResult(intent,1)
        }
    }



}
