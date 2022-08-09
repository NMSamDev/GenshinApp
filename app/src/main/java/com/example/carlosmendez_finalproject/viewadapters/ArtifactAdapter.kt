package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.GeneralListItemBinding

class ArtifactAdapter(
    private val list:MutableList<String> = mutableListOf()
    ): RecyclerView.Adapter<ArtifactAdapter.ArtifactViewHolder>() {
    inner class ArtifactViewHolder(private val binding: GeneralListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            val baseUrl = "https://api.genshin.dev/artifacts/"
            binding.apply {
                Glide.with(ivItemIcon)
                    .load("$baseUrl$item/circlet-of-logos")
//                    .load("$baseUrl$item/flower-of-life")
                    .error(R.drawable.paimon_icon_0)
                    .into(ivItemIcon)
                tvItemName.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtifactViewHolder {
        return ArtifactViewHolder(
            GeneralListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtifactViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNewList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}