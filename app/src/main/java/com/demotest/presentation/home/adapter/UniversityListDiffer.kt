package com.demotest.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.demotest.domin.university.model.UniversityItem

class UniversityListDiffer: DiffUtil.ItemCallback<UniversityItem>() {
    override fun areItemsTheSame(oldItem: UniversityItem, newItem: UniversityItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: UniversityItem, newItem: UniversityItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}