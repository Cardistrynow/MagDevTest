package com.magdev.infrastructure.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.magdev.infrastructure.injection.ActivityScope
import com.magdev.infrastructure.injection.FragmentScope
import com.magdev.presentation.main.MainActivity
import com.magdev.presentation.main.MainModule
import com.magdev.presentation.main.home.HomeFragment
import com.magdev.presentation.main.home.HomeModule
import com.magdev.presentation.splash.SplashActivity
import com.magdev.presentation.splash.SplashModule

@Module
interface ActivityBinderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class, ErrorHandleModule::class])
    fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class, ErrorHandleModule::class])
    fun bindMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class, ErrorHandleModule::class])
    fun bindHomeFragment(): HomeFragment

}