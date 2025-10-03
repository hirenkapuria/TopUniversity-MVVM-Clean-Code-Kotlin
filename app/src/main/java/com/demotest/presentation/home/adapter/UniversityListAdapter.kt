package com.demotest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demotest.databinding.ItemUniversityListBinding
import com.demotest.domin.university.model.UniversityItem

class UniversityListAdapter(
    private val onUniversityClick: (String?) -> Unit
) : ListAdapter<UniversityItem, UniversityListAdapter.ViewHolder>(UniversityListDiffer()) {

    inner class ViewHolder(val binding: ItemUniversityListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUniversityListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem = getItem(position)
        holder.itemView.apply {
            holder.binding.tvSrNo.text = position.plus(1).toString()
            holder.binding.tvUniversityName.text = userItem.name
            holder.binding.tvWebsite.text = userItem.webPage

        }
        holder.binding.root.setOnClickListener {
            onUniversityClick(userItem.webPage)
        }
    }
}