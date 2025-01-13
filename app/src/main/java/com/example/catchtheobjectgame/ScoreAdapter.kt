package com.example.catchtheobjectgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catchtheobjectgame.data.ScoreEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScoreAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private val items = mutableListOf<ScoreEntity>()

    fun setScores(newList: List<ScoreEntity>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ScoreViewHolder(
        itemView: View,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val textScore: TextView = itemView.findViewById(R.id.textScore)
        private val textDate: TextView = itemView.findViewById(R.id.textDate)

        fun bind(item: ScoreEntity) {
            textScore.text = "Score: ${item.score}"
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            textDate.text = sdf.format(Date(item.dateTime))

            itemView.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }
}
