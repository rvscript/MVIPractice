package com.example.mvipractice.ui.vm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvipractice.R
import com.example.mvipractice.data.model.PhotosItem
import com.example.mvipractice.ui.Fragment1
import com.example.mvipractice.ui.Fragment2

class MainAdapter(
    private val context: Context,
    private val photos: ArrayList<PhotosItem>,
    private val fragment: Fragment1
): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val img_avatar = itemView.findViewById<ImageView>(R.id.imageViewAvatar)
        val tv_title = itemView.findViewById<TextView>(R.id.item_title)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(photo: PhotosItem) {
            Glide.with(img_avatar.context)
                .load(photo.thumbnailUrl)
                .into(img_avatar)
            tv_title.text = "ID: ${photo.id}, ${photo.title}\n${photo.url}"
        }

        override fun onClick(v: View?) {
            val fragmentManager = fragment.parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, Fragment2())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun addData(list: List<PhotosItem>) {
        photos.addAll(list)
    }
}