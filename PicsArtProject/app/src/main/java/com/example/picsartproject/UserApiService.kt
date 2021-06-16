package com.example.picsartproject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("users/signin.json")
    fun addUser(@Body user: UserRequest) : Call<UserResponse>

    @GET("stage/photos/freetoedit/search.json?q=&offset=0&limit=50")
    fun getPhotoResponnse() : Call<PhotoResponse>
}