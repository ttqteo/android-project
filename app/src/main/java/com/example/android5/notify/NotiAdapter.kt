package com.example.android5.notify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android5.R
import com.example.android5.model.Noti

class   NotiAdapter : ListAdapter<Noti, NotiAdapter.NotiViewHolder>(NotiDiffUtil()) {

    class NotiDiffUtil : DiffUtil.ItemCallback<Noti>() {
        override fun areItemsTheSame(oldItem: Noti, newItem: Noti): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Noti, newItem: Noti): Boolean {
            return oldItem == newItem
        }

    }

    public override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotiViewHolder {
        return NotiViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotiViewHolder, position: Int) { //do du lieu vào tưng item
        val noti = getItem(position)
        holder.bindData(noti)
    }

    class NotiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup) : NotiViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.row_noti, parent, false)
                return NotiViewHolder(view)
            }
        }

        fun bindData(noti: Noti) {
//            val ivAvt = itemView.findViewById<ImageView>(R.id.avt)
            val tvDepartmentNoti = itemView.findViewById<TextView>(R.id.department_noti)
            val tvTitleNoti = itemView.findViewById<TextView>(R.id.title_noti)
            val tvTimeNoti = itemView.findViewById<TextView>(R.id.time_noti)


//            ivAvt.setImageResource(feed.avt)
            tvDepartmentNoti.text = noti.name
            tvTitleNoti.text = noti.title_status
            tvTimeNoti.text = noti.hour

        }
    }
}