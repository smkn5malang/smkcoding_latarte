package com.rizkarhm.latarte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

data class Recipes (val listTitle: String )

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>(){
    private val titles = arrayOf("Chapter One",
        "Chapter Two", "Chapter Three", "Chapter Four",
        "Chapter Five", "Chapter Six", "Chapter Seven",
        "Chapter Eight")

    private val content = arrayOf("Item one details", "Item two details",
        "Item three details", "Item four details",
        "Item file details", "Item six details",
        "Item seven details", "Item eight details")

    private val images = intArrayOf(R.drawable.logo,
        R.drawable.profil, R.drawable.logo,
        R.drawable.profil, R.drawable.logo,
        R.drawable.profil, R.drawable.logo,
        R.drawable.profil)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemContent: TextView

        init {
            itemImage = itemView.findViewById(R.id.imgList)
            itemTitle = itemView.findViewById(R.id.titleList)
            itemContent = itemView.findViewById(R.id.contentList)

            itemView.setOnClickListener { v: View  ->
                var position: Int = getAdapterPosition()

                Snackbar.make(v, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipes_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemContent.text = content[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}