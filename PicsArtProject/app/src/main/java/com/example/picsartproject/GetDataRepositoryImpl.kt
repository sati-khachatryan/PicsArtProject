package com.example.picsartproject

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


const val USER_SAVED_KEY = "user_saved"
val TAG = "GetDataRepositoryImpl"

class GetDataRepositoryImpl(val preference: SharedPreferences) : GetDataRepository {

    val service = RetrofitFactory.retrofit

    var currentUser: UserResponse? = null
    var photos: List<PhotoItem>? = null
    var message: String? = null


    override suspend fun addUser(user: UserRequest, shouldSave: Boolean): Pair<String?, UserResponse?> {
        val async = GlobalScope.async(Dispatchers.IO) {
            service.addUser(user).execute()
        }
        val result = async.await()
        if (result.isSuccessful) {
            currentUser = result.body()
            if (shouldSave) {
                currentUser?.let {
                    saveUser(it)
                }
            }

        } else {
            message = result.message()
        }

        return Pair(message, currentUser)
    }

    override suspend fun getPhotoResponnse(): Pair<String?, List<PhotoItem>?> {
        val async = GlobalScope.async(Dispatchers.IO) {
            service.getPhotoResponnse().execute()
        }
        val result = async.await()
        if (result.isSuccessful) {
            photos = result.body()?.photooItems
        } else {
            message = result.message()
        }

        return Pair(message, photos)
    }

    override suspend fun isUserSaved(): Boolean {
        return preference.getBoolean(USER_SAVED_KEY, false)
    }

    private fun saveUser(user: UserResponse) {
        preference.edit().putBoolean(USER_SAVED_KEY, true).apply()
    }


}
