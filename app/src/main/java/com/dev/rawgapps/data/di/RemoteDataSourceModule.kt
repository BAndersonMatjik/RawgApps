package com.dev.rawgapps.data.di

import com.dev.rawgapps.data.remote.datasource.RawgRemoteDataSource
import com.dev.rawgapps.data.remote.datasource.RawgRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindsRawgRemoteDataSource(rawgRemoteDataSourceImpl: RawgRemoteDataSourceImpl):RawgRemoteDataSource
}

