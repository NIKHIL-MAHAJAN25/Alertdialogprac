package com.nikhil.alertdialogprac.recyler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.alertdialogprac.R
import com.nikhil.alertdialogprac.listpackage.Inter
import com.nikhil.alertdialogprac.listpackage.student

class RecyclerAdapter(var arrayList: ArrayList<student>,var listener1:Inter):RecyclerView.Adapter<RecyclerAdapter.Viewholder>() {
        class Viewholder (var view: View): RecyclerView.ViewHolder(view){
            var name=view.findViewById<TextView>(R.id.tvName)
            var rollno=view.findViewById<TextView>(R.id.tvrollno)
            var subject=view.findViewById<TextView>(R.id.tvsubject)
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            var view= LayoutInflater.from(parent.context).inflate(R.layout.recycleritem,parent,false)
            return Viewholder(view)
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun onBindViewHolder(holder: Viewholder, position: Int) {
//        holder.name.setText(arrayList[position].name)
//        holder.rollno.setText(arrayList[position].rollNo)
//        holder.subject.setText(arrayList[position].subject)
            holder.apply {
                name.setText(arrayList[position].name)
                rollno.setText(arrayList[position].rollNo)
                subject.setText(arrayList[position].subject)
                itemView.setOnClickListener {
                    listener1.onitemclick(position)
                }
                itemView.setOnLongClickListener {
                    listener1.onitemlong(position)
                    true
                }
            }
        }

    }

