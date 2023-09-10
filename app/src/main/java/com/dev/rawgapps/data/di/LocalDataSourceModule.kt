package com.dev.rawgapps.data.di

import com.dev.rawgapps.data.local.datasource.RawgLocalDataSource
import com.dev.rawgapps.data.local.datasource.RawgLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindsRawgLocalDataSource(rawgLocalDataSourceImpl: RawgLocalDataSourceImpl): RawgLocalDataSource
}