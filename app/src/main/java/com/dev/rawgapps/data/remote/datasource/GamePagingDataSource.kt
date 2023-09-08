package com.dev.rawgapps.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev.rawgapps.domain.Game
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GamePagingDataSource @Inject constructor(private val rawgRemoteDataSource: RawgRemoteDataSource) :
    PagingSource<Int, Game>() {

    companion object{
        const val PAGE_SIZE = 10
    }
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val currentPage = params.key ?: 1
            val games = rawgRemoteDataSource.getGames(currentPage, PAGE_SIZE)
            games.fold(onSuccess = {result->
                return LoadResult.Page(result, prevKey = if (currentPage==1) null else currentPage-1, nextKey = if (result.isEmpty()) null else currentPage+1 )
            }, onFailure = {
                LoadResult.Error(it)
            })

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}