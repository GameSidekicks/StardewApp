package com.gamesidekicks.data.di

import android.content.Context
import androidx.room.Room
import com.gamesidekicks.data.domain.VillagersDatabase
import com.gamesidekicks.data.IMainRepository
import com.gamesidekicks.data.Repository
import com.gamesidekicks.data.api.IRemoteRepository
import com.gamesidekicks.data.api.RemoteRepository
import com.gamesidekicks.data.domain.ILocalRepository
import com.gamesidekicks.data.domain.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        @RemoteDataSource remoteDataSource: IRemoteRepository,
        @LocalDataSource localDataSource: ILocalRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): IMainRepository {
        return Repository(remoteDataSource, localDataSource, ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideRemoteDataSource(): IRemoteRepository = RemoteRepository

    @Singleton
    @LocalDataSource
    @Provides
    fun provideLocalDataSource(
        villagersDatabase: VillagersDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ILocalRepository {
        return LocalRepository(villagersDatabase.villagerDao(), ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): VillagersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            VillagersDatabase::class.java,
            "Villagers.db"
        ).build()
    }
}

