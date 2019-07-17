package com.yuichi.passwordlistmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

class MyAdapter(val context:Context,val list:List<dataarray>):BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view=convertView?:createView(parent)

        val viewHolder = view.tag as ViewHolder

        val data = getItem(position)

        //Toast.makeText(context,data?.name,Toast.LENGTH_SHORT).show()

        viewHolder.txtviewname.text=data?.name
        viewHolder.txtviewurl.text=data?.url
        viewHolder.txtviewid.text=data?.id
        viewHolder.txtviewpassword.text=data?.password

        return view
    }

    override fun getItem(position: Int): dataarray?{
        return list[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

    class ViewHolder(view:View){
        val txtviewname = view.findViewById<TextView>(R.id.txtViewName)
        val txtviewurl = view.findViewById<TextView>(R.id.txtViewURL)
        val txtviewid = view.findViewById<TextView>(R.id.txtViewID)
        val txtviewpassword = view.findViewById<TextView>(R.id.txtViewPassword)
    }

    private fun createView(parent: ViewGroup?):View{
        val view = inflater.inflate(R.layout.activity_detaile,parent,false)
        view.tag=ViewHolder(view)
        return view
    }
}