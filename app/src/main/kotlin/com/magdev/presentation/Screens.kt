package com.magdev.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.magdev.presentation.main.home.HomeFragment


object Screens {

    object Home: FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return HomeFragment.newInstance()
        }
    }
}
