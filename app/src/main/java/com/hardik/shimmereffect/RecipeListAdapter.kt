package com.hardik.shimmereffect

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hardik.shimmereffect.R.layout
import com.hardik.shimmereffect.RecipeListAdapter.RecipeViewHolder
import kotlinx.android.synthetic.main.recipe_list_item.view.chef
import kotlinx.android.synthetic.main.recipe_list_item.view.description
import kotlinx.android.synthetic.main.recipe_list_item.view.name
import kotlinx.android.synthetic.main.recipe_list_item.view.price
import kotlinx.android.synthetic.main.recipe_list_item.view.thumbnail
import kotlinx.android.synthetic.main.recipe_list_item.view.timestamp

/**
 * Created by Hardik on 18/10/18.
 */
class RecipeListAdapter(
  private val context: Context,
  private val recipeList: List<RecipeResponse>
) : Adapter<RecipeViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecipeViewHolder {
    val itemView = LayoutInflater.from(parent.context)
        .inflate(layout.recipe_list_item, parent, false)
    return RecipeViewHolder(itemView)
  }

  override fun onBindViewHolder(
    holder: RecipeViewHolder,
    position: Int
  ) {

    holder.bind(recipeList[position])
  }

  inner class RecipeViewHolder(itemView: View) : ViewHolder(itemView) {

    fun bind(recipeResponse: RecipeResponse) {

      itemView.name.text = recipeResponse.name
      itemView.chef.text = String.format("By %s", recipeResponse.chef)
      itemView.description.text = recipeResponse.description
      itemView.price.text = String.format("Price: ₹ %s", recipeResponse.price)
      itemView.timestamp.text = recipeResponse.timestamp
      Glide.with(context)
          .load(recipeResponse.thumbnail)
          .into(itemView.thumbnail)
    }
  }

  // recipe
  override fun getItemCount(): Int {
    return recipeList.size
  }
}