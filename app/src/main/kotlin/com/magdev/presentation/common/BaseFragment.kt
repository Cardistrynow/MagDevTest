package com.magdev.presentation.common

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.LayoutRes
import com.magdev.R
import com.magdev.infrastructure.extensions.toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutId: Int) : MvpAppCompatFragment(layoutId),
                                                        HasAndroidInjector,
                                                        IBaseView,
                                                        IBackButtonListener {

    @Inject protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        return false
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
}
