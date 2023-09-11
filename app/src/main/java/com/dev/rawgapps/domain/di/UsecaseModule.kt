package com.dev.rawgapps.domain.di

import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecase
import com.dev.rawgapps.domain.usecase.AddFavoriteGameUsecaseImpl
import com.dev.rawgapps.domain.usecase.GetDetailGameUsecase
import com.dev.rawgapps.domain.usecase.GetDetailUsecaseImpl
import com.dev.rawgapps.domain.usecase.GetFavoriteGamesUsecase
import com.dev.rawgapps.domain.usecase.GetFavoriteGamesUsecaseImpl
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
    @Binds
    abstract fun bindAddFavoritegameUsecase(addFavoriteGameUsecase: AddFavoriteGameUsecaseImpl):AddFavoriteGameUsecase
    @Binds
    abstract fun bindGetFavoriteGamesUsecase(getFavoriteGamesUsecase: GetFavoriteGamesUsecaseImpl):GetFavoriteGamesUsecase

}