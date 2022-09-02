package com.magdev.presentation.common

import android.app.AlertDialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.*
import com.magdev.infrastructure.extensions.toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import com.magdev.R
import moxy.MvpAppCompatDialogFragment
import javax.inject.Inject

abstract class BaseDialogFragment : MvpAppCompatDialogFragment(),
                                    HasAndroidInjector,
                                    IBaseView {

    @Inject protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onResume() {
        initDialogWidth()
        super.onResume()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showMessage(messageType: MessageType, message: String) {
        when (messageType) {
            MessageType.TOAST -> requireContext().toast(message)
            MessageType.ALERT -> showAlert(message)
        }
    }

    override fun showMessage(messageType: MessageType, resId: Int, vararg args: String) {
        showMessage(messageType, getString(resId, *args))
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.alert_title_error)
            .setMessage(message)
            .setPositiveButton(R.string.alert_button_ok) { dialog, which -> dialog.dismiss() }
            .show()
    }

    /**
     * Replace default wrap_content width of FragmentDialog with default width of AlertDialog
     * Override with empty Unit if needed wrap_content width.
     */
    open protected fun initDialogWidth() {
        dialog?.window?.apply {
            val windowSize = Point()
            windowManager.defaultDisplay.getSize(windowSize)
            setLayout(windowSize.x, windowSize.y)
            setGravity(Gravity.CENTER)
        }
    }
}
