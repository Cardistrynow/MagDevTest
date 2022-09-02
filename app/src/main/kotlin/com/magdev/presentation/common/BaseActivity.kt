package com.magdev.presentation.common

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.magdev.infrastructure.extensions.toast
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.magdev.R
import moxy.MvpAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes layoutId: Int) : MvpAppCompatActivity(layoutId), IBaseView, HasAndroidInjector {

    @Inject protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        val backButtonListener = supportFragmentManager.findFragmentById(R.id.content) as? IBackButtonListener

        if (backButtonListener?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }

    override fun showMessage(messageType: MessageType, message: String) {
        when (messageType) {
            MessageType.TOAST -> toast(message)
            MessageType.ALERT -> showAlert(message)
        }
    }

    override fun showMessage(messageType: MessageType, resId: Int, vararg args: String) {
        showMessage(messageType, getString(resId, *args))
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title_error)
            .setMessage(message)
            .setPositiveButton(R.string.alert_button_ok) { dialog, which -> dialog.dismiss() }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                fragment?.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
