package com.example.android5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android5.model.Schedule

class   ScheduleAdapter : ListAdapter<Schedule, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffUtil()) {

    class ScheduleDiffUtil : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.subject_id == newItem.subject_id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }

    }

    public override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) { //do du lieu vào tưng item
        val schedule = getItem(position)
        holder.bindData(schedule)
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup) : ScheduleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.fragment_row_schedule, parent, false)
                return ScheduleViewHolder(view)
            }
        }

        fun bindData(schedule: Schedule) {
            val tvSubjectId = itemView.findViewById<TextView>(R.id.subject_id)
            val tvSubjectName = itemView.findViewById<TextView>(R.id.subject_name)
            val tvClassGroup = itemView.findViewById<TextView>(R.id.class_group)
            val tvSchedule = itemView.findViewById<TextView>(R.id.schedule)

            tvSubjectId.text = schedule.subject_id
            tvSubjectName.text = schedule.subject_name
            tvClassGroup.text = schedule.class_group
            tvSchedule.text = schedule.schedule
        }
    }
}