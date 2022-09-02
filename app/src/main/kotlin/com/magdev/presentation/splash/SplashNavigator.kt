package com.magdev.presentation.splash

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.magdev.R
import javax.inject.Inject

class SplashNavigator @Inject constructor(activity: SplashActivity) : AppNavigator(activity, R.id.content)
