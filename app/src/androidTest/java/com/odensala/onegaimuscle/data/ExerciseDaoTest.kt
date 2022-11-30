package com.odensala.onegaimuscle.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class ExerciseDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: ExerciseDatabase
    private lateinit var dao: ExerciseDao

    @Before
    fun setup() {
        hiltRule.inject()
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