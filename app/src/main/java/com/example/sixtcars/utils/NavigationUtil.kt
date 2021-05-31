package com.example.sixtcars.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

object NavigationUtil {

    fun showDialog(
        dialogFragment: DialogFragment,
        fragmentManager: FragmentManager?,
        isCancelable: Boolean = false,
        tag: String? = dialogFragment.tag
    ) {

        dialogFragment.isCancelable = isCancelable

        val currentFragment = fragmentManager?.findFragmentByTag(tag)
        if (currentFragment != null && currentFragment.isAdded)
            return
        if (fragmentManager != null) {
            dialogFragment.show(fragmentManager, tag)
            fragmentManager.executePendingTransactions()
        }
    }

    fun startActivity(
        currentActivity: Activity,
        cls: Class<*>,
        bundle: Bundle?,
        keepCurrentActivity: Boolean = true
    ) {
        val intent = Intent(currentActivity, cls)
        bundle?.let { intent.putExtras(it) }
        ActivityCompat.startActivity(currentActivity, intent, bundle)
        if (!keepCurrentActivity)
            currentActivity.finish()
    }
}

