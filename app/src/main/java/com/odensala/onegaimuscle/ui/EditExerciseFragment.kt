package com.odensala.onegaimuscle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.onegaimuscle.R
import com.example.onegaimuscle.databinding.FragmentEditExerciseBinding


class EditExerciseFragment : Fragment(R.layout.fragment_edit_exercise) {

    private var _binding: FragmentEditExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditExerciseBinding.inflate(inflater, container, false)
        val spinner: Spinner = binding.spinnerWorkouts
        ArrayAdapter.createFromResource(
            requireContext(), R.array.exercises_array,
            androidx.transition.R.layout.support_simple_spinner_dropdown_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}