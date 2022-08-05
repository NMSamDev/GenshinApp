package com.example.carlosmendez_finalproject.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.databinding.CharacterFragmentBinding
import com.example.carlosmendez_finalproject.databinding.CharacterItemListBinding
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.view.CharacterFragment

class CharacterAdapter(
    private val list: MutableList<String> = mutableListOf(),
    private val openDetails: (String) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>()  {

    inner class CharacterViewHolder(private val binding: CharacterItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(item: String) {
            val baseUrl = "https://api.genshin.dev/characters/"
            binding.apply {
                Glide.with(ivCharacterIcon)
                    .load("$baseUrl$item/icon-big")
                    .error("$baseUrl$item/icon")
                    .into(ivCharacterIcon)
                tvCharacter.text = item.formatName()

                root.setOnClickListener {
                    openDetails(item)
                }
            }
        }
    }

    fun String.formatName(): String  = this.split("-").joinToString(" ") {it ->
            it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }

    fun setCharacterList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


