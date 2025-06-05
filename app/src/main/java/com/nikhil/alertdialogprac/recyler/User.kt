package com.nikhil.alertdialogprac.recyler

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String?= null,
    var description: String?= null,
)