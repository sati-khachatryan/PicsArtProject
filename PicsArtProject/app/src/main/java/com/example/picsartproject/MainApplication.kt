package com.example.picsartproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

const val PREF_NAME = "main_pref"
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {

            single<GetDataRepository> {
                GetDataRepositoryImpl(preference = get())
            }

            single<SharedPreferences> {
                getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            }


            viewModel<MainViewModel> {
                MainViewModel(getDataRepository = get())
            }

        }

        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }

    }
}