package com.mylektop.chucknorisjokes.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylektop.chucknorisjokes.databinding.FragmentCategoriesBinding
import com.mylektop.chucknorisjokes.main.MainActivity

/**
 * Created by iddangunawan on 11/18/20
 */
class CategoriesFragment : Fragment() {

    private lateinit var viewBinding: FragmentCategoriesBinding
    private lateinit var categoryAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCategoriesBinding.inflate(inflater, container, false).apply {
            vm = (activity as MainActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupCategory()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    private fun setupCategory() {
        val viewModel = viewBinding.vm
        if (viewModel != null) {
            categoryAdapter = CategoriesAdapter(viewModel.categoriesList, viewModel)
            viewBinding.rvCategories.adapter = categoryAdapter
            viewBinding.rvCategories.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        }
    }

    companion object {
        fun newInstance() = CategoriesFragment().apply {

        }
    }
}