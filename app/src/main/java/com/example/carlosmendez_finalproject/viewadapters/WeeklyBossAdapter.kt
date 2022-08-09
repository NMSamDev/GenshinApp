package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.GeneralListItemBinding

class WeeklyBossAdapter(
    private val list: MutableList<String> = mutableListOf()
): RecyclerView.Adapter<WeeklyBossAdapter.WeeklyBossViewHolder>() {
    inner class WeeklyBossViewHolder(private val binding: GeneralListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            val baseUrl = "https://api.genshin.dev/boss%2Fweekly-boss/"
            binding.apply {
                Glide.with(ivItemIcon)
                    .load("$baseUrl$item/icon")
                    .error(R.drawable.paimon_icon_0)
                    .into(ivItemIcon)

                tvItemName.text = item.formatName()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyBossViewHolder {
        return WeeklyBossViewHolder(
            GeneralListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeeklyBossViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun String.formatName(): String  = this.split("-").joinToString(" ") {it ->
        it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }

    fun setNewList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}