package com.example.indianicassigment.data.local.db

import com.example.indianicassigment.data.model.api.MaxtraResponse


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
interface IDbHelper {
        fun getAllMaxtra(): List<MaxtraResponse>
//
        fun insertMaxtra(person: MaxtraResponse)

        fun insertPersonAll(person: List<MaxtraResponse>)
//
        fun updateMaxtra(person: MaxtraResponse)
//
        fun deleteMaxtra()
//
         fun getMaxtra(id:Int): MaxtraResponse
}