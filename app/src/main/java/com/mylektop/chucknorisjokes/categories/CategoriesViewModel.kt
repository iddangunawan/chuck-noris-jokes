package com.mylektop.chucknorisjokes.categories

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.mylektop.chucknorisjokes.data.source.JokesDataRepository
import com.mylektop.chucknorisjokes.data.source.JokesDataSource
import com.mylektop.chucknorisjokes.util.SingleLiveEvent

/**
 * Created by iddangunawan on 11/18/20
 */
class CategoriesViewModel(
    application: Application,
    private val jokesDataRepository: JokesDataRepository
) : AndroidViewModel(application) {
    val categoriesList: ObservableList<String> = ObservableArrayList()
    internal val openCategory = SingleLiveEvent<String>()

    fun start() {
        getJokesCategoriesData()
    }

    private fun getJokesCategoriesData() {
        jokesDataRepository.getJokesCategoriesData(object :
            JokesDataSource.GetJokesCategoriesDataCallback {
            override fun onDataLoaded(categoriesData: MutableList<String?>) {
                with(categoriesList) {
                    clear()
                    addAll(categoriesData)
                }
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(), "No Data Found", Toast.LENGTH_LONG).show()
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(), "Error: $msg", Toast.LENGTH_LONG).show()
            }
        })
    }
}