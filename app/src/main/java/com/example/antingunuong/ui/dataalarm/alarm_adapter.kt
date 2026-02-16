//package com.example.antingunuong.ui.dataalarm
//import android.view.View
//import android.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//class AlarmAdapter(private val onToggle: (Alarm, Boolean) -> Unit) :
//    ListAdapter<Alarm, AlarmAdapter.AlarmViewHolder>(AlarmDiffCallback()) {
//    inner class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
//        val tvLabel: TextView = itemView.findViewById(R.id.tvLabel)
//        val tvDays: TextView = itemView.findViewById(R.id.tvDays)
//        val swActive: SwitchCompat = itemView.findViewById(R.id.swActive)
//        val imgHabit: ImageView = itemView.findViewById(R.id.imgHabit)
//        fun bind(alarm: Alarm) {
//            tvTime.text = alarm.time
//            tvLabel.text = alarm.label
//            tvDays.text = alarm.days
//            imgHabit.setImageResource(alarm.habitIcon)
//            swActive.isChecked = alarm.isEnabled
//            updateAlpha(alarm.isEnabled)
//            swActive.setOnCheckedChangeListener { _, isChecked ->
//                onToggle(alarm, isChecked)
//                updateAlpha(isChecked)
//            }
//        }
//        private fun updateAlpha(enabled: Boolean) {
//            itemView.alpha = if (enabled) 1.0f else 0.5f
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
//        return AlarmViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}
//class AlarmDiffCallback : DiffUtil.ItemCallback<Alarm>() {
//    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm) = oldItem.id == newItem.id
//    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm) = oldItem == newItem
//}