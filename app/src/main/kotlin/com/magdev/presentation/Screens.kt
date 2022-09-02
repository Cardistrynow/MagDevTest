package com.magdev.presentation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.magdev.presentation.main.MainActivity
import com.magdev.presentation.main.home.HomeFragment


object Screens {

    object Main: ActivityScreen {
        override fun createIntent(context: Context): Intent {
            return MainActivity.createIntent(context)
        }
    }

    object Home: FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return HomeFragment.newInstance()
        }
    }
}
