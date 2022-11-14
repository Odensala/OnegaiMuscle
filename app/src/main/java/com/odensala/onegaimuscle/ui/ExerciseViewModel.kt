package com.odensala.onegaimuscle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.odensala.onegaimuscle.data.ExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(exerciseDao: ExerciseDao) : ViewModel() {
    val searchQuery = MutableStateFlow("")
    private val exerciseFlow = searchQuery.flatMapLatest {
        exerciseDao.getExercises(it)
    }
    val exercises = exerciseFlow.asLiveData()
}