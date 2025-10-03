package com.demotest.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demotest.data.home.entity.UniversityEntity
import com.demotest.databinding.ItemUniversityListBinding

class UniversityPagingListAdapter(
    private val onItemClick: (website: String?) -> Unit
) : PagingDataAdapter<UniversityEntity, UniversityPagingListAdapter.UniversityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding = ItemUniversityListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UniversityViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = getItem(position)
        university?.let { holder.bind(it) }
    }

    class UniversityViewHolder(
        private val binding: ItemUniversityListBinding,
        private val onItemClick: (website: String?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(university: UniversityEntity) {
            binding.tvSrNo.text = position.plus(1).toString()
            binding.tvUniversityName.text = university.name
            binding.tvWebsite.text = university.webPage

            binding.root.setOnClickListener {
                onItemClick(university.webPage)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UniversityEntity>() {
            override fun areItemsTheSame(
                oldItem: UniversityEntity,
                newItem: UniversityEntity
            ) = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: UniversityEntity,
                newItem: UniversityEntity
            ) = oldItem == newItem
        }
    }
}
