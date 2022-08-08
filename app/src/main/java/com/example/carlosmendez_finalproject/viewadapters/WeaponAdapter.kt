package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.GeneralListItemBinding
import com.example.carlosmendez_finalproject.model.WeaponResponse

class WeaponAdapter(
    private val list: MutableList<String> = mutableListOf()
): RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {
    inner class WeaponViewHolder(private val binding: GeneralListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: String){
            val baseUrl = "https://api.genshin.dev/weapons/"
            binding.apply {
                Glide.with(ivItemIcon)
                    .load("$baseUrl$item/icon")
                    .error(R.drawable.paimon_icon_0)
                    .into(ivItemIcon)

                tvItemName.text = item.formatName()
            }
        }
    }

    fun setNewList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun String.formatName(): String  = this.split("-").joinToString(" ") {it ->
        it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder(
            GeneralListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}