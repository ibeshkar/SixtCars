package com.example.sixtcars.di.component

import android.app.Application
import com.example.sixtcars.di.module.ActivityModule
import com.example.sixtcars.di.module.ApiModule
import com.example.sixtcars.di.module.ContextModule
import com.example.sixtcars.di.module.ViewModelModule
import com.example.sixtcars.utils.AppController
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ContextModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)
}