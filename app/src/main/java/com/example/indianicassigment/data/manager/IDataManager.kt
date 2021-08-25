package com.example.indianicassigment.data.manager

import com.example.indianicassigment.data.local.db.IDbHelper
import com.example.indianicassigment.data.local.prefs.ISharedPrefsHelper
import com.example.indianicassigment.data.remote.IApiHelper


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
interface IDataManager: IDbHelper, ISharedPrefsHelper, IApiHelper