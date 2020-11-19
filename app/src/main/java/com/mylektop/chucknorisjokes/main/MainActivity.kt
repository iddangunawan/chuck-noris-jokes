package com.mylektop.chucknorisjokes.main

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.mylektop.chucknorisjokes.R
import com.mylektop.chucknorisjokes.categories.CategoriesFragment
import com.mylektop.chucknorisjokes.categories.CategoriesViewModel
import com.mylektop.chucknorisjokes.util.obtainViewModel
import com.mylektop.chucknorisjokes.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mActivity = this
        setupViewModel()
        setupFragment()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openCategory.observe(this@MainActivity, Observer {
                onRepoClicked(it!!)
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameCategories)
        CategoriesFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameCategories)
        }
    }

    fun onRepoClicked(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(mActivity, R.color.colorPrimary))
        customTabsIntent.launchUrl(mActivity, Uri.parse(url))
    }

    fun obtainViewModel(): CategoriesViewModel = obtainViewModel(CategoriesViewModel::class.java)
}