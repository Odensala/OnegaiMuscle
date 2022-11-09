package com.odensala.onegaimuscle.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onegaimuscle.databinding.ExerciseItemBinding
import com.odensala.onegaimuscle.data.Exercise

class ExerciseAdapter : ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding =
            ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class ExerciseViewHolder(private val binding: ExerciseItemBinding) :
        ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.apply {
                textviewExerciseOne.text = exercise.name
                textviewExerciseOneWeight.text = exercise.weight
                textviewExerciseOneDetails.text = exercise.details
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise) =
            newItem.id == oldItem.id


        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise) =
            oldItem == newItem
    }
}