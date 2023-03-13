package com.kou.gitflyer.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kou.gitflyer.data.entity.User
import com.kou.gitflyer.databinding.ItemUserBinding
import javax.inject.Inject

class UsersAdapter @Inject constructor() :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var UserItemClickListener: ((String) -> Unit)? = null

    private var users = listOf<User>()


    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]


        with(holder.binding) {
            imUser.load(user.avatar_url)
            username.text=user.login
        }

    }

    override fun getItemCount() = users.size

    fun updateUsers(users: List<User>) {
        val diffResult =
            DiffUtil.calculateDiff(SimpleCallback(this.users, users) { it.login })
        this.users = users
        diffResult.dispatchUpdatesTo(this)
    }

    fun setUserClickListener(callback: ((String) -> Unit)) {
        this.UserItemClickListener = callback
    }


    inner class SimpleCallback<T>(private val oldItems: List<T>, private val newItems: List<T>,
                                  private val itemIdGetter: (T) -> Any) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            itemIdGetter(oldItems[oldItemPosition]) == itemIdGetter(newItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size
    }

}