package com.dev.rawgapps.data.di

import com.dev.rawgapps.data.remote.datasource.GamePageDataSourceFacade
import com.dev.rawgapps.data.remote.datasource.GamePagingDataSource
import com.dev.rawgapps.data.remote.datasource.PageDataSourceFacade
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PageDataSourceFacadeModule{
    @Binds
    abstract fun bindsGamePageDataSourceFacade(gamePageDataSourceFacade: GamePageDataSourceFacade): PageDataSourceFacade<GamePagingDataSource>
}