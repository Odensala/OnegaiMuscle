package com.odensala.onegaimuscle.ui

import androidx.lifecycle.ViewModel
import com.odensala.onegaimuscle.data.ExerciseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(private val exerciseDao: ExerciseDao) : ViewModel() {

}