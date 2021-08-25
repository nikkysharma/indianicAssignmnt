package com.example.indianicassigment.data.model.api

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class MaxtraResponse  (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var doctorUserId: String? = null,
    var requestUserId: String? = null,
    var locationLat: String? = null,
    var locationLong: String? = null,
    var locationAddress: String? = null,
    var doctorCurrentLat: String? = null,
    var doctorCurrentLong: String? = null,
    var helpNeededFor: String? = null,
    var description: String? = null,
    var amount: String? = null,
    var requestStatus: String? = null,
    var paymentStatus: String? = null,
    var reachedTo: String? = null,
    var treatment: String? = null,
    var reason: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var name: String? = null,
    var email: String? = null,
    var mobileNumber: String? = null,
    var userImage: String? = null,
    var path: String? = null,
)