package com.odensala.onegaimuscle.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class ExerciseDaoTest {

    private lateinit var database: ExerciseDatabase
    private lateinit var dao: ExerciseDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ExerciseDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.exerciseDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insert_whenExerciseIsInserted_exerciseIsInDatabase() =
        runTest {
            val exercise = Exercise("Overhead press", "40kg", "3 sets 5 reps", id = 1)
            dao.insert(exercise)

            dao.getExercises("").test {
                val listOfExercises = awaitItem()
                assertThat(listOfExercises).contains(exercise)
                cancel()
            }
        }

    @Test
    fun delete_whenExerciseIsDeleted_exerciseIsNotInDatabase() =
        runTest {
            val exercise = Exercise("Overhead press", "40kg", "3 sets 5 reps", id = 1)
            dao.insert(exercise)
            dao.delete(exercise)

            dao.getExercises("").test {
                val listOfExercises = awaitItem()
                assertThat(listOfExercises).doesNotContain(exercise)
            }
        }

    @Test
    fun update_whenExerciseIsUpdated_exerciseIsUpdated() =
        runTest {
            val exercise = Exercise("Overhead press", "40kg", "3 sets 5 reps", id = 1)
            dao.insert(exercise)
            val updatedExercise = Exercise("Overhead press", "100kg", "3 sets 100 reps", id = 1)
            dao.update(updatedExercise)

            dao.getExercises("").test {
                val listOfExercises = awaitItem()
                assertThat(listOfExercises).contains(updatedExercise)
            }
        }
}