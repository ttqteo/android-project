package com.example.android5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android5.database.Feed


class   FeedAdapter : ListAdapter<Feed, FeedAdapter.FeedViewHolder>(FeedDiffUtil()) {

    class FeedDiffUtil : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }

    }

    public override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) { //do du lieu vào tưng item
        val feed = getItem(position)
        holder.bindData(feed)
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup) : FeedViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.row_feed, parent, false)
                return FeedViewHolder(view)
            }
        }

        fun bindData(feed: Feed) {
//            val ivAvt = itemView.findViewById<ImageView>(R.id.avt)
            val tvName = itemView.findViewById<TextView>(R.id.name)
            val tvHour = itemView.findViewById<TextView>(R.id.hour)
            val tvStatus = itemView.findViewById<TextView>(R.id.status)
            val tvTitle_status = itemView.findViewById<TextView>(R.id.title_status)
            val tvLink_home_web = itemView.findViewById<TextView>(R.id.link_home_web)

//            ivAvt.setImageResource(feed.avt)
            tvName.text = feed.name
            tvHour.text = feed.hour.toString()
            tvStatus.text = feed.status
            tvTitle_status.text = feed.title_status
            tvLink_home_web.text = feed.link_home_web
        }
    }
}