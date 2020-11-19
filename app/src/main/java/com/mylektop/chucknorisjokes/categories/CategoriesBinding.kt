package com.mylektop.chucknorisjokes.categories

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iddangunawan on 11/19/20
 */
object CategoriesBinding {
    @BindingAdapter("app:categoryList")
    @JvmStatic
    fun setCategoryList(recycler: RecyclerView, categoryData: MutableList<String>) {
        with(recycler.adapter as CategoriesAdapter) {
            replaceData(categoryData)
        }
    }
}