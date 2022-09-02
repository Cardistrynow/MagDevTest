package com.magdev.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.magdev.databinding.ActivityMainBinding
import com.magdev.infrastructure.extensions.intentFor
import com.magdev.infrastructure.extensions.longToast
import com.magdev.infrastructure.extensions.onClick
import com.magdev.presentation.common.viper.AbstractViperActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : AbstractViperActivity<MainPresenter, MainNavigator>(),
                     IMainView {

    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    override lateinit var presenter: MainPresenter

    @ProvidePresenter
    override fun providePresenter(): MainPresenter {
        return presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return context.intentFor<MainActivity>()
        }
    }
}
