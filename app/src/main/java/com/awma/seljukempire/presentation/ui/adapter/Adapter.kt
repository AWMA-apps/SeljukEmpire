package com.awma.seljukempire.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awma.seljukempire.R
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import com.bumptech.glide.Glide

class Adapter(private var list: List<Any?>, val onItemClick: (Any) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_events, parent, false)
                EventsViewHolder(view)
            }

            2 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_characters, parent, false)
                CharactersViewHolder(view)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)
                AdViewHolder(view)
            }
        }
    }

    fun updateList(newList: List<Any?>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener { onItemClick(list[position]!!) }
        when (list[position]) {
            is Characters -> {
                val item = list[position] as Characters
                val vh = holder as CharactersViewHolder
                Glide.with(vh.image.context).load(item.imageUrl).into(vh.image)
                Glide.with(vh.civ.context).load(item.charPhotoUrl).into(vh.civ)
                vh.title.text = item.titleText!!["ar-العربية"]
            }

            is Events -> {
                val item = list[position] as Events
                val vh = holder as EventsViewHolder
                Glide.with(vh.image.context).load(item.imageUrl).into(vh.image)
                vh.title.text = item.titleText!!["ar-العربية"]
            }

            else -> {

            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Characters -> 2
            is Events -> 1
            else -> 0
        }
    }

    inner class EventsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image = v.findViewById<ImageView>(R.id.ivImage)
        val title = v.findViewById<TextView>(R.id.tvTitle)
    }

    inner class CharactersViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image = v.findViewById<ImageView>(R.id.ivImage)
        val title = v.findViewById<TextView>(R.id.tvTitle)
        val civ = v.findViewById<ImageView>(R.id.civChar)
    }

    inner class AdViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }
}