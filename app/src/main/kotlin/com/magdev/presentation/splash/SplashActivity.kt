package com.magdev.presentation.splash

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.magdev.R
import com.magdev.databinding.ActivitySplashBinding
import com.magdev.infrastructure.extensions.intentFor
import com.magdev.presentation.common.viper.AbstractViperActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SplashActivity : AbstractViperActivity<SplashPresenter, SplashNavigator>(R.layout.activity_splash),
                       ISplashView {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var permissionDisposable: Disposable

    @InjectPresenter
    override lateinit var presenter: SplashPresenter

    @ProvidePresenter
    override fun providePresenter(): SplashPresenter {
        return presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.checkPermissions()
    }

    override fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            presenter.onPermissionsResult(true)
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        permissionDisposable = RxPermissions(this)
            .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION)
            .subscribe { permission ->
                when {
                    permission.granted -> {
                        presenter.onPermissionsResult(true)
                    }
                    permission.shouldShowRequestPermissionRationale -> {
                        presenter.onPermissionsResult(false, true)
                    }
                    else -> presenter.onPermissionsResult(false, false)
                }
            }
    }

    override fun showPermissionsRationale() {
        AlertDialog.Builder(this)
            .setTitle(R.string.permissions_rationale_title)
            .setMessage(R.string.permissions_rationale_message)
            .setPositiveButton(R.string.settings) { dialog, _ ->
                presenter.onSettingsClick()
            }
            .setOnDismissListener {
                presenter.onPermissionsResult(false, true)
            }
            .show()
    }

    override fun navigateToSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", this.packageName, null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return context.intentFor<SplashActivity>()
        }
    }
}
