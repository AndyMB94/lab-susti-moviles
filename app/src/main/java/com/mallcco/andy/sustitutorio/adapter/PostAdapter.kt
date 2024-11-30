package com.mallcco.andy.sustitutorio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mallcco.andy.sustitutorio.databinding.ItemPostBinding
import com.mallcco.andy.sustitutorio.model.Post

class PostAdapter(
    private val posts: List<Post>,
    private val onItemClick: (Post) -> Unit,
    private val onItemLongClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.titleText.text = post.title
            binding.bodyText.text = post.body
            binding.root.setOnClickListener { onItemClick(post) }
            binding.root.setOnLongClickListener {
                onItemLongClick(post)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}
