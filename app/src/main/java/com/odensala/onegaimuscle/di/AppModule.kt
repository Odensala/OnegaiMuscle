package com.odensala.onegaimuscle.di

import android.app.Application
import androidx.room.Room
import com.odensala.onegaimuscle.data.ExerciseDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, ExerciseDatabase::class.java, "exercise_database")

    @Provides
    fun provideExerciseDao(db: ExerciseDatabase) = db.exerciseDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
}