package com.example.recyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.ItemSuperhero
import com.example.recyclerview.R
import com.example.recyclerview.Superhero
import com.example.recyclerview.databinding.ActivityItemSuperheroBinding
import com.example.recyclerview.databinding.ActivityMainBinding

class SuperHeroViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ActivityItemSuperheroBinding.bind(view)

    fun render (superhero: Superhero){

        binding.tvSuperHeroName.text = superhero.superHero
        binding.tvRealName.text = superhero.realName
        binding.tvPublisher.text = superhero.publisher
        Glide.with(binding.ivSuperHero.context)
            .load(superhero.photo)
            .into(binding.ivSuperHero)

        binding.ivSuperHero.setOnClickListener {
            Toast.makeText(binding.ivSuperHero.context, superhero.realName, Toast.LENGTH_SHORT).show()
        }
        this.itemView.setOnClickListener {
            Toast.makeText(binding.ivSuperHero.context, superhero.superHero, Toast.LENGTH_SHORT).show()
        }
    }
}