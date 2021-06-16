package com.example.picsartproject

import retrofit2.Response
import retrofit2.http.Body

interface GetDataRepository {

    suspend fun addUser(user: UserRequest, shouldSave: Boolean) : Pair<String?, UserResponse?>
    suspend fun getPhotoResponnse() : Pair<String?, List<PhotoItem>?>

    suspend fun isUserSaved(): Boolean
}