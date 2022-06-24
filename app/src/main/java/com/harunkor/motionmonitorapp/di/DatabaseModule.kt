package com.harunkor.motionmonitorapp.di

import android.content.Context
import androidx.room.Room
import com.harunkor.motionmonitorapp.data.local.AppDatabase
import com.harunkor.motionmonitorapp.data.local.dao.MoveDao
import com.harunkor.motionmonitorapp.data.local.repository.MoveRepositoryImp
import com.harunkor.motionmonitorapp.domain.repository.MoveRepository
import com.harunkor.motionmonitorapp.domain.usecase.MoveUseCase
import com.harunkor.motionmonitorapp.domain.usecase.move.AddMove
import com.harunkor.motionmonitorapp.domain.usecase.move.AllMovements
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoveDao(appDatabase: AppDatabase): MoveDao {
        return appDatabase.moveDao
    }

    @Provides
    fun provideMoveRepository(moveDao: MoveDao): MoveRepository = MoveRepositoryImp(moveDao)

    @Provides
    fun provideMoveUseCase(moveRepository: MoveRepository) = MoveUseCase(
        allMovements = AllMovements(moveRepository),
        addMove = AddMove(moveRepository)
    )


}