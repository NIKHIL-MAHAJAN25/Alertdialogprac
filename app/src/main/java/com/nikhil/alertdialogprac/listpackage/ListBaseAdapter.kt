package com.nikhil.alertdialogprac.listpackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.nikhil.alertdialogprac.R
import java.util.ArrayList
//initializing class student and interface ofd listener
class ListBaseAdapter(var arrayList: ArrayList<student>,var listener:Inter):BaseAdapter()
{
    //overriding functions by implementing members
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var view=LayoutInflater.from(parent?.context).inflate(R.layout.list_item,parent,false)
        var name = view.findViewById<TextView>(R.id.tvName)
        name.setText(arrayList[position].name)
        var rollno = view.findViewById<TextView>(R.id.tvrollno)
        rollno.setText(arrayList[position].rollNo)
        var subject = view.findViewById<TextView>(R.id.tvsubject)
        subject.setText(arrayList[position].subject)
        view.setOnClickListener {
            listener.onitemclick(position)
        }
        view.setOnLongClickListener {
            listener.onitemlong(position)
            true
        }
        return view
    }
}