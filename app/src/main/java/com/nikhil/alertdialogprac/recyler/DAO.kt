package com.nikhil.alertdialogprac.recyler

import androidx.room.*
@Dao
interface DAO {

    @Insert
    fun insertTodo(notesEntity: User)

    @Query("SELECT * FROM User")
    fun getList() : List<User>

    @Update
    fun updateTodoEntity(notesEntity: User)

    @Delete
    fun deleteTodoEntity(notesEntity: User)
}