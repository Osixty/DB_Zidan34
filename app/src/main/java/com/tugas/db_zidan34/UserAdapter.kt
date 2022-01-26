package com.tugas.db_zidan34

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugas.roomdb_zidan34.data.User
import kotlinx.android.synthetic.main.list_user.view.*

class UserAdapter(private val user: ArrayList<User>, private var listener:OnAdapterListener)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user1  = user[position]
        holder.view.text_user.text = user1.nama
        holder.view.text_sekolah.text = user1.sekolah
        holder.view.text_user.setOnClickListener {
            listener.onClick(user1)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(user1)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(user1)
        }
    }

    override fun getItemCount() = user.size

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<User>){
        user.clear()
        user.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(user: User)
        fun onUpdate(user: User)
        fun onDelete(user: User)

    }


}