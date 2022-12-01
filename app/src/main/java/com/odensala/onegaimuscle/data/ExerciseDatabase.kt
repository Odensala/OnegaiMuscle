package com.odensala.onegaimuscle.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.odensala.onegaimuscle.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Exercise::class], version = 1)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    class Callback @Inject constructor(
        private val exerciseDao: Provider<ExerciseDao>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = exerciseDao.get()
            applicationScope.launch {
                dao.insert(Exercise("Bench press", "22,5kg", "Ezpz"))
                dao.insert(Exercise("Dips", "bodyweight", "8 on first set, 6 on second"))
                dao.insert(Exercise("Dumbbell incline  skullcrushers", "7kg", "A bit shaky"))
                dao.insert(Exercise("Dumbbell curls", "10kg", "8 on first set, 5 on second"))
                dao.insert(
                    Exercise(
                        "Dumbbell lateral rows",
                        "2kg",
                        "bruh these are still hard at 2kg lmao"
                    )
                )
                dao.insert(Exercise("Bench press", "22,5kg", "Ezpz"))
                dao.insert(Exercise("Dips", "bodyweight", "8 on first set, 6 on second"))
                dao.insert(Exercise("Dumbbell incline  skullcrushers", "7kg", "A bit shaky"))
                dao.insert(Exercise("Dumbbell curls", "10kg", "8 on first set, 5 on second"))
                dao.insert(
                    Exercise(
                        "Dumbbell lateral rows",
                        "2kg",
                        "bruh these are still hard at 2kg lmao"
                    )
                )
            }
        }
    }
}