package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.DropListItemBinding
import com.example.carlosmendez_finalproject.model.Drop

class DropAdapter(
    private val list: MutableList<Drop> = mutableListOf(),
    private val bossId: String
): RecyclerView.Adapter<DropAdapter.DropViewHolder>() {
    inner class DropViewHolder(private val binding: DropListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Drop) {
            val baseUrl = "https://api.genshin.dev/boss%2Fweekly-boss/"
            binding.apply {
                Glide.with(ivDrop)
                    .load(R.drawable.paimon_icon_0)
                    .placeholder(R.drawable.element_pyro_icon)
                    .error(R.drawable.paimon_icon_0)
                    .into(ivDrop)
                tvDropName.text = item.name
                tvDropSource.text = item.source
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropViewHolder {
        return DropViewHolder(
            DropListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DropViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNewList(newList: List<Drop>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
        println(itemCount)
    }

    fun String.formatName(): String  = this.replace(" ", "-").replace("'","-").lowercase()
}