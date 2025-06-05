package com.nikhil.alertdialogprac.recyler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[User::class],version=1,exportSchema=true)
abstract class NotesDatabase :RoomDatabase(){
    abstract fun DAO():DAO
    companion object{
        private var notesDatabase:NotesDatabase?=null
        fun getInstance(context:Context):NotesDatabase{
            if(notesDatabase==null)
            {
                notesDatabase= Room.databaseBuilder(context,NotesDatabase::class.java,
                    "NotesDatabase")
                    .allowMainThreadQueries()
                    .build()
            }
            return notesDatabase!!
        }
    }
}