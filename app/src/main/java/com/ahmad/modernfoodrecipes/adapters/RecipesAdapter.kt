package com.ahmad.modernfoodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmad.modernfoodrecipes.databinding.RecipesRowLayoutBinding
import com.ahmad.modernfoodrecipes.models.FoodRecipe
import com.ahmad.modernfoodrecipes.models.Result
import com.ahmad.modernfoodrecipes.util.RecipeDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipe = emptyList<Result>()

    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(result: Result){
                binding.result = result
                binding.executePendingBindings()

            }

        companion object {
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false )
                return MyViewHolder(binding)
            }
        }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipe[position]
        holder.bind(currentRecipe)
    }

    fun setData(newdata:FoodRecipe){
        val recipeDiffUtil = RecipeDiffUtil(recipe, newdata.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        recipe = newdata.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}