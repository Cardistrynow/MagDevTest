package com.magdev.presentation.common.viper

import androidx.annotation.LayoutRes
import com.magdev.presentation.common.BaseActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class AbstractNavigationActivity<N : Navigator>(@LayoutRes layoutId: Int) : BaseActivity(layoutId) {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var navigator: N

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
