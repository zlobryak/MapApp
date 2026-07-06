package ru.netology.nmedia.ui.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.nmedia.ui.data.repository.PointRepository
import ru.netology.nmedia.ui.data.repository.PointRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsPostRepository(impl: PointRepositoryImpl): PointRepository
}