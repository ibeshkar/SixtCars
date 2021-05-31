package com.example.sixtcars.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sixtcars.data.factory.ViewModelFactory
import com.example.sixtcars.ui.main.CarsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CarsViewModel::class)
    protected abstract fun carsViewModel(carsViewModel: CarsViewModel): ViewModel
}