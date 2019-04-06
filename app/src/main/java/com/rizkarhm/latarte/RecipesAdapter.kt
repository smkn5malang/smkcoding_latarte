package com.rizkarhm.latarte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes_list.view.*

data class Recipes (val name: String)

class RecipesAdapter (private val list:ArrayList<Recipes>) : RecyclerView.Adapter<RecipesAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recipes_list,parent,false))
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.titleList.text = list?.get(position)?.name
        holder.view.contentList.text = list?.get(position)?.name

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}