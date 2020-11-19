package com.mylektop.chucknorisjokes.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mylektop.chucknorisjokes.R
import com.mylektop.chucknorisjokes.databinding.CategoryItemBinding

/**
 * Created by iddangunawan on 11/19/20
 */
class CategoriesAdapter(
    private var categoryData: MutableList<String>,
    private var categoryViewModel: CategoriesViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val categoryItemBinding: CategoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item, parent, false
        )
        return CategoryHolder(categoryItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = categoryData[position]
        val actionListener = object : CategoriesItemActionListener {
            override fun onCategoryClicked() {
                categoryViewModel.openCategory.value = data
            }
        }
        (holder as CategoryHolder).bindRows(data, actionListener)
    }

    override fun getItemCount(): Int = categoryData.size

    fun replaceData(repoDa: MutableList<String>) {
        setList(repoDa)
    }

    fun setList(repoData: MutableList<String>) {
        this.categoryData = repoData
        notifyDataSetChanged()
    }

    class CategoryHolder(binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val repoItemBinding = binding

        fun bindRows(repoData: String, userActionListener: CategoriesItemActionListener) {
            repoItemBinding.data = repoData
            repoItemBinding.action = userActionListener
            repoItemBinding.executePendingBindings()
        }
    }

}