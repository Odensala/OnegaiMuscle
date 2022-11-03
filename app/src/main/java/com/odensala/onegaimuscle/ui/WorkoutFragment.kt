package com.odensala.onegaimuscle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onegaimuscle.R
import com.example.onegaimuscle.databinding.FragmentWorkoutBinding


class WorkoutFragment : Fragment(R.layout.fragment_workout) {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        binding.fabAddWorkout.setOnClickListener {
            findNavController().navigate(R.id.action_progressFragment_to_addWorkoutFragment)
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}