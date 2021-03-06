package com.example.sixtcars.utils

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.sixtcars.di.component.DaggerAppComponent
import com.example.sixtcars.di.module.ApiModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppController : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()

        appContext = this

        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}