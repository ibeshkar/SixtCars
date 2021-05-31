package com.example.sixtcars.ui.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixtcars.R
import com.example.sixtcars.data.entity.Car
import com.example.sixtcars.ui.main.CarListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_list.*


class CarListDialog : BottomSheetDialogFragment() {

    companion object {
        const val CARS = "CARS"

        @JvmStatic
        fun newInstance(cars: ArrayList<Car>): CarListDialog {
            val args = Bundle()
            args.putSerializable(CARS, cars)
            val fragment = CarListDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private val listAdapter: CarListAdapter by lazy {
        CarListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.activity_list, container, false)


     @RequiresApi(Build.VERSION_CODES.N)
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         initUI()
     }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initUI() {
        val cars = arguments?.getSerializable(CARS) as ArrayList<Car>?
        if (cars != null) {
            listAdapter.setItems(cars)
            list.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = listAdapter
            }
        }
    }


    

}