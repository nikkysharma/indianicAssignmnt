package com.example.projectsetwithmvvmdagger.data.local.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.indianicassigment.data.model.api.MaxtraResponse


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Dao
interface MaxtraDAO {

    @Query("select * from user_data")
    fun getAllPerson():List<MaxtraResponse>

    @Insert(onConflict = REPLACE)
    fun insertPerson(person: MaxtraResponse)

    @Insert(onConflict = REPLACE)
    fun insertPersonAll(person: List<MaxtraResponse>)

    @Update(onConflict = REPLACE)
    fun updatePerson(person: MaxtraResponse)

    @Query("DELETE FROM user_data")
    fun deletePerson()

    @Query("select * from user_data where id = :id")
    fun getPerson(id:Int): MaxtraResponse
}