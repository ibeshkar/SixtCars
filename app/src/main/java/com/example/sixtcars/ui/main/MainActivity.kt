package com.example.sixtcars.ui.main


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sixtcars.BR
import com.example.sixtcars.R
import com.example.sixtcars.data.entity.Car
import com.example.sixtcars.databinding.ActivityMainBinding
import com.example.sixtcars.ui.base.BaseActivity
import com.example.sixtcars.ui.dialog.CarListDialog
import com.example.sixtcars.utils.NavigationUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, CarsViewModel>(), OnMapReadyCallback {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mMap: GoogleMap
    private var cars: ArrayList<Car> = arrayListOf()

    override fun getBindingVariable(): Pair<Int, Any?> {
        return Pair(BR._all, mViewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setViewModel() {
        mViewModel = ViewModelProvider(this, viewModelFactory).get(CarsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        setupMap()
    }

    private fun initUI() {
        btnShowList.isEnabled = false
        btnShowList.setOnClickListener {
            NavigationUtil.showDialog(CarListDialog.newInstance(cars), supportFragmentManager,true)
        }
    }

    private fun setupMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mViewModel?.getCars()?.observe(this, {
            when {
                it.isLoading -> {
                    loading.visibility = View.VISIBLE
                }
                it.isSuccess -> {
                    loading.visibility = View.GONE
                    cars = it?.data as ArrayList<Car>
                    setCarsOnMap(it.data)
                    btnShowList.isEnabled = true
                }
                it.isError -> {
                    loading.visibility = View.GONE
                    Log.d(
                        TAG,
                        "error->${it.throwable?.let { it1 -> mViewModel?.errorHandler(it1) }}"
                    )
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setCarsOnMap(data: MutableList<Car>?) {
        if (mViewModel?.isEmptyList(data) == true) {
            Log.d(TAG, "Cars list is empty!!!")
        } else {
            var lat = 0.0
            var lng = 0.0
            data?.stream()?.forEach { car ->
                lat += car.latitude
                lng += car.longitude
                Glide.with(this)
                    .asBitmap()
                    .load(car.carImageUrl)
                    .into(object : CustomTarget<Bitmap>(120, 120) {
                        override fun onResourceReady(
                            bitmap: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(LatLng(car.latitude, car.longitude))
                                    .title("Driver: ${car.name}")
                                    .snippet("License Plate: ${car.licensePlate}")
                                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                            )
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(LatLng(car.latitude, car.longitude))
                                    .title("Driver: ${car.name}")
                                    .snippet("License Plate: ${car.licensePlate}")
                                    .icon(BitmapDescriptorFactory.defaultMarker())
                            )
                        }
                    })

                // Set camera to center of markers
                val count = data.size
                val center = LatLng(lat / count, lng / count)
                val cameraPosition = CameraPosition.Builder()
                    .target(center)
                    .zoom(12f)
                    .tilt(30f)
                    .build()
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }

        }
    }
}