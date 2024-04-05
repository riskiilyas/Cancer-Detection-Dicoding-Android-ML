package com.dicoding.asclepius

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.databinding.ItemNewsBinding

class NewsAdapter(private var stories: List<Article>, private val onClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        Glide.with(holder.itemView).load(story.urlToImage).into(holder.binding.ivItemPhoto)
        holder.binding.tvItemName.text = story.title
        holder.binding.textViewDescription.text = story.description

        holder.itemView.setOnClickListener {
            onClick(story)
        }
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: List<Article>) {
        stories = newList
        notifyDataSetChanged()
    }
}