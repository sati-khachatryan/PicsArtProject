package com.example.picsartproject

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(val getDataRepository: GetDataRepository) : ViewModel(),KoinComponent {

    private var _user = MutableLiveData<UserResponse>()
    val user : LiveData<UserResponse>
        get() = _user
    private var _responsePair = MutableLiveData<Pair<String?, UserResponse?>>()
    val responsePair : LiveData<Pair<String?, UserResponse?>>
        get() = _responsePair
    private var _error = MutableLiveData<String?>()
    val error : LiveData<String?>
        get() = _error
    private var _photoResponsePair = MutableLiveData<Pair<String?, List<PhotoItem>?>>()
    val photoResponsePair : LiveData<Pair<String?, List<PhotoItem>?>>
        get() = _photoResponsePair
    private var _photo = MutableLiveData<List<PhotoItem>>()
    val photo : LiveData<List<PhotoItem>>
        get() = _photo

    private var _userSaved = MutableLiveData<Boolean>()
    val userSaved : LiveData<Boolean>
        get() = _userSaved



     fun getUser(userRequest: UserRequest, shouldSave: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val responsePair = getDataRepository.addUser(userRequest, shouldSave)

            _responsePair.postValue(responsePair)
            if (responsePair.first == null) {
                _user.postValue(responsePair.second)
            }
//            } else {
//                _error.postValue(responsePair.first)
//            }

        }

    }

    fun getPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
           val photoResponsePair = getDataRepository.getPhotoResponnse()

            _photoResponsePair.postValue(photoResponsePair)
            if (photoResponsePair.first == null) {
                _photo.postValue(photoResponsePair.second)
            } else {
                _error.postValue(photoResponsePair.first)
            }


        }

    }

    fun isUserSaved() {
        viewModelScope.launch(Dispatchers.IO) {
            _userSaved.postValue(getDataRepository.isUserSaved())
        }
    }




}