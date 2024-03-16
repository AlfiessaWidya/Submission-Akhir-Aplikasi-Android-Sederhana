package com.dicoding.myrecyclervew

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFilmAdapter(private val listFilms: ArrayList<Film>) : RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val context = holder.itemView.context
        val filmChar = listFilms[position]
        Glide.with(holder.itemView.context)
            .load(filmChar.photo)
            .apply(RequestOptions())
            .into(holder.imgPhoto)
        holder.tvName.text = filmChar.name
        holder.tvDescription.text = filmChar.desc
        holder.tvReleased.text = filmChar.released
        holder.tvRating.text = filmChar.rating
        holder.tvActor.text = filmChar.actor

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_NAME, filmChar.name)
            intentDetail.putExtra(DetailActivity.EXTRA_PHOTO, filmChar.photo)
            intentDetail.putExtra(DetailActivity.EXTRA_DESC, filmChar.desc)
            intentDetail.putExtra(DetailActivity.EXTRA_RELEASED, filmChar.released)
            intentDetail.putExtra(DetailActivity.EXTRA_RATING, filmChar.rating)
            intentDetail.putExtra(DetailActivity.EXTRA_ACTOR, filmChar.actor)
            intentDetail.putExtra(DetailActivity.EXTRA_LINK, filmChar.link)
            context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listFilms.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_detail_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_detail_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvReleased: TextView = itemView.findViewById(R.id.tv_item_released)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
        val tvActor: TextView = itemView.findViewById(R.id.tv_item_actor)
    }
}
