package com.magdev.infrastructure.injection.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import com.magdev.infrastructure.serializers.GsonSerializer
import com.magdev.infrastructure.serializers.ISerializer
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSerializer(gson: Gson): ISerializer = GsonSerializer(gson)

}
