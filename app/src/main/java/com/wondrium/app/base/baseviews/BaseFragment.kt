package com.wondrium.app.base.baseviews

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch


abstract class BaseFragment : Fragment() {
    var pageSource: String = "Default"
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun closeKeyBoard(): Boolean {
        val view = activity?.currentFocus
        if (view != null) {
            val imm =
                activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return false
    }

    fun toastMsg(mesage: String) {
        Toast.makeText(context, mesage, Toast.LENGTH_SHORT).show()
    }

    var mProgressDialog: ProgressDialog? = null
    fun showLoader() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context)
        }
        mProgressDialog?.setTitle("Please wait...")
        mProgressDialog?.setMessage("Loading...")
        mProgressDialog?.show()


    }

    var errorTextView: TextView? = null
    fun showError(msg: String) {
        if (mProgressDialog != null) {
            mProgressDialog?.dismiss()
        }

    }

    fun hideLoader() {
        mProgressDialog?.dismiss()

    }

    override fun onDestroy() {
        super.onDestroy()
        mProgressDialog?.dismiss()
    }

    fun <T> convertDataToStringList(leadSource: ArrayList<T>): ArrayList<String> {
        val result = ArrayList<String>()
        leadSource.forEach { result.add(it.toString()) }
        return result
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
        }
    }


    override fun onStart() {
        super.onStart()
    }

    abstract fun getBackPressEnable(): Boolean

    abstract fun getTitle(): String


    open fun <T : Parcelable> navigateWithParcelableData(
        destinationId: Int,
        data: T,
        bundleTag: String = "data"
    ) {
        val bundle = Bundle()
        bundle.putParcelable(bundleTag, data)
        bundle.putString(PAGE_SOURCE_NAVIGATE, pageSource)
        findNavController().navigate(
            destinationId,
            bundle
        )
    }


    interface Validator {
        fun sucess()
        fun failure()
    }

    fun validateLogin(validator: Validator? = null, func: () -> Any = {}) {

    }
}