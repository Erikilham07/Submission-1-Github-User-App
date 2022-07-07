package com.dicoding.mysubmission1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mysubmission1.databinding.GithubUserBinding


class ListUserAdapter (val listUser : ArrayList<User>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: GithubUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = GithubUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, name, location, avatar, company) = listUser[position]
        holder.binding.tvItemUserName.text = username
        holder.binding.tvName.text = name
        holder.binding.tvItemCompany.text = company
        Glide.with(holder.binding.imgItemPhoto.context)
            .load(avatar)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemLocation.text = location

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int =listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}