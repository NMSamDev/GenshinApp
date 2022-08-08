package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.CharacterItemListBinding
import com.example.carlosmendez_finalproject.model.WeaponResponse

class WeaponAdapter(
    private val list: MutableList<String> = mutableListOf()
): RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {
    inner class WeaponViewHolder(private val binding: CharacterItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: String){
            val baseUrl = "https://api.genshin.dev/weapons/"
            binding.apply {
                Glide.with(ivCharacterIcon)
                    .load("$baseUrl$item/icon")
                    .error(R.drawable.paimon_icon_0)
                    .into(ivCharacterIcon)

                tvCharacter.text = item.formatName()
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
            CharacterItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}