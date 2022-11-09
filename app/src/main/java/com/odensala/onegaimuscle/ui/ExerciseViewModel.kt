package com.odensala.onegaimuscle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.odensala.onegaimuscle.data.ExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(exerciseDao: ExerciseDao) : ViewModel() {
    val exercises = exerciseDao.getExercises().asLiveData()

}