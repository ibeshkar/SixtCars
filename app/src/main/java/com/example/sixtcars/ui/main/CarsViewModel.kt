package com.example.sixtcars.ui.main

import androidx.lifecycle.liveData
import com.example.sixtcars.data.remote.Resource
import com.example.sixtcars.data.repository.MainRepository
import com.example.sixtcars.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CarsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {


    fun getCars() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = mainRepository.getCars()))
        } catch (throwable: Throwable) {
            emit(
                Resource.error(
                    data = null,
                    msg = throwable.localizedMessage ?: "Error Occurred!",
                    throwable = throwable
                )
            )
        }
    }

}