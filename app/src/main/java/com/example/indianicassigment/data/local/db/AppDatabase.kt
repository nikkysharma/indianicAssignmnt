package com.example.indianicassigment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.projectsetwithmvvmdagger.data.local.db.dao.MaxtraDAO


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Database(entities = arrayOf(MaxtraResponse::class),
            version = 1,exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun maxtraDAO(): MaxtraDAO
}