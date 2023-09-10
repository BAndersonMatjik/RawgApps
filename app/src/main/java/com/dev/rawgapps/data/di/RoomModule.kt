package com.dev.rawgapps.data.di

import android.content.Context
import androidx.room.Room
import com.dev.rawgapps.data.local.config.RawgDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context:Context): RawgDatabase = Room.databaseBuilder(context,RawgDatabase::class.java,RawgDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    @Provides
    @Singleton
    fun provideFavoriteDao(database: RawgDatabase)=database.favoriteDao()
}