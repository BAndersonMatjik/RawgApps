package com.dev.rawgapps.data.di

import com.dev.rawgapps.data.repository.RawgRepository
import com.dev.rawgapps.data.repository.RawgRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideRawgRepository(rawgRepositoryImpl: RawgRepositoryImpl):RawgRepository
}