package com.project.pop_flake.di



import android.content.Context
import androidx.room.Room
import com.project.pop_flake.data.local.Dao
import com.project.pop_flake.data.local.MovieDatabase
import com.project.pop_flake.data.remote.RemoteDataSource
import com.project.pop_flake.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModuleBind {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource


}


@InstallIn(SingletonComponent::class)
@Module
 class DatabaseModuleBind {
     @Singleton
     @Provides
     fun provideDatabase(@ApplicationContext context: Context):MovieDatabase{
         return Room.databaseBuilder(context.applicationContext,
           MovieDatabase::class.java,"movie_database").allowMainThreadQueries().build()
     }
 }
