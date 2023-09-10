package com.dev.rawgapps.domain.di

import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailUsecaseImpl
import com.dev.rawgapps.domain.usecase.GetGamesUsecase
import com.dev.rawgapps.domain.usecase.GetGamesUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class UsecaseModule {
    @Binds
    abstract fun bindGetGamesUsecase(getGamesUsecase: GetGamesUsecaseImpl):GetGamesUsecase

    @Binds
    abstract fun bindGetDetailGameUsecase(getDetailUsecaseImpl: GetDetailUsecaseImpl):GetDetailGameUsecase
}