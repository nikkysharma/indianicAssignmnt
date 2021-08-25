package com.example.indianicassigment.data.local.db



import com.example.indianicassigment.data.model.api.MaxtraResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
@Singleton
class DbHelper : IDbHelper {

    val appDatabase: AppDatabase

    @Inject
    constructor(appDatabase: AppDatabase) {
        this.appDatabase = appDatabase
    }
    override fun getAllMaxtra(): List<MaxtraResponse> = appDatabase.maxtraDAO().getAllPerson()

    override fun insertMaxtra(person: MaxtraResponse) {
        appDatabase.maxtraDAO().insertPerson(person)
    }

    override fun insertPersonAll(person: List<MaxtraResponse>) {
        appDatabase.maxtraDAO().insertPersonAll(person)
    }

    override fun updateMaxtra(person: MaxtraResponse) {
        appDatabase.maxtraDAO().updatePerson(person)
    }

    override fun deleteMaxtra() {
        appDatabase.maxtraDAO().deletePerson()
    }

    override fun getMaxtra(id: Int): MaxtraResponse = appDatabase.maxtraDAO().getPerson(id)

}