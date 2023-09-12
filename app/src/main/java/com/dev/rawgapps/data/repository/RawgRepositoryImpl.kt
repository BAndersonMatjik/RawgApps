package com.dev.rawgapps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev.rawgapps.common.Constants
import com.dev.rawgapps.data.local.datasource.RawgLocalDataSource
import com.dev.rawgapps.data.remote.datasource.GamePagingDataSource
import com.dev.rawgapps.data.remote.datasource.PageDataSourceFacade
import com.dev.rawgapps.data.remote.datasource.RawgRemoteDataSource
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class RawgRepositoryImpl @Inject constructor(
    private val gamePageDataSourceFacade: PageDataSourceFacade<GamePagingDataSource>,
    private val gameRemoteDataSource: RawgRemoteDataSource,
    private val gameLocalDataSource: RawgLocalDataSource
) : RawgRepository {
    override suspend fun getGames(keyword: String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(Constants.PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                gamePageDataSourceFacade.create(keyword)
            }
        ).flow
    }

    override suspend fun getGame(slug: String): Flow<Result<Game>> {
        return flow {
            emit(gameRemoteDataSource.getGameDetail(slug))
        }
    }

    override suspend fun getGameFavorite(slug: String): Flow<Result<Game>> {
        return gameLocalDataSource.getGameBySlag(slag = slug)
    }

    override suspend fun addFavoriteGame(game: Game) {
        gameLocalDataSource.addFavoriteGame(game)
    }

    override suspend fun removeFavoriteGame(slug: String) {
        gameLocalDataSource.deleteFavoriteGame(slug)
    }

    override suspend fun getFavoriteGames(): Flow<PagingData<Game>> =
        gameLocalDataSource.getFavoriteGames()


}