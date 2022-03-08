package com.project.pop_flake.di

import com.project.pop_flake.data.repo.Repository
import com.project.pop_flake.data.repo.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}