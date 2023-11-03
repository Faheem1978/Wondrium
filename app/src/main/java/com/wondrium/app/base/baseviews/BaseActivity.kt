package com.wondrium.app.base.baseviews

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var mProgressDialog: ProgressDialog? = null
    fun showLoader() {
        try {
            if (mProgressDialog == null)
                mProgressDialog = ProgressDialog(this)

            mProgressDialog!!.setTitle("Please wait...")
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()

        }


    }

    fun hideLoader() {
        mProgressDialog?.hide()

    }
}