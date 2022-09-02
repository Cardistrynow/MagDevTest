package com.magdev.presentation.main

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.magdev.R
import javax.inject.Inject

class MainNavigator @Inject constructor(activity: MainActivity) : AppNavigator(activity, R.id.container)
