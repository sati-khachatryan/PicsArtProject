package com.example.picsartproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


const val ERROR_MSG = "username or password is Incorrect"
class MainActivity : AppCompatActivity(), KoinComponent {

    val mainViewModel: MainViewModel by viewModel()

    var usernameEdt: EditText? = null
    var passwordEdt: EditText? = null
    var rememberBox: CheckBox? = null
    var errorText: TextView? = null
    var login: Button? = null
    var username = ""
    var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEdt = findViewById(R.id.username)
        passwordEdt = findViewById(R.id.password)
        errorText = findViewById(R.id.error_text)


        login = findViewById(R.id.login)
        rememberBox = findViewById<CheckBox>(R.id.rememberMe)

        login?.setOnClickListener {
            username = usernameEdt?.text.toString()
            password = passwordEdt?.text.toString()
            login()
        }

        mainViewModel.isUserSaved()
        mainViewModel.userSaved.observe(this, Observer {
            if (it) {
                addFragment()
            } else {
                showViews()
            }
        })


    }

    fun showViews() {
        login?.visibility = View.VISIBLE
        usernameEdt?.visibility = View.VISIBLE
        passwordEdt?.visibility = View.VISIBLE
        rememberBox?.visibility = View.VISIBLE
    }

    fun login() {
        mainViewModel.getUser(
            userRequest = UserRequest(username, password),
            shouldSave = rememberBox?.isChecked ?: false
        )
        mainViewModel.responsePair.observe(this, Observer {
            if (it.first != null) {
                errorText?.visibility = View.VISIBLE
                errorText?.setText(ERROR_MSG)
                Log.d("redmi", "request error")
            } else {
                addFragment()
            }
        })
    }

    fun addFragment() {
        login?.visibility = View.INVISIBLE
        usernameEdt?.visibility = View.INVISIBLE
        passwordEdt?.visibility = View.INVISIBLE


        supportFragmentManager.beginTransaction().apply {
            this.add(R.id.fragmentContainer, FirstFragment())
            commit()
        }
        mainViewModel.getPhoto()

    }
}


