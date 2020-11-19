package com.mylektop.chucknorisjokes.data.source.remote

import android.annotation.SuppressLint
import com.mylektop.chucknorisjokes.api.ApiService
import com.mylektop.chucknorisjokes.api.dao.CategoriesDataDao
import com.mylektop.chucknorisjokes.data.JokesData
import com.mylektop.chucknorisjokes.data.source.JokesDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by iddangunawan on 11/18/20
 */
object JokesDataRemoteSource : JokesDataSource {

    private val apiService = ApiService.create()

    @SuppressLint("CheckResult")
    override fun getJokesCategoriesData(callback: JokesDataSource.GetJokesCategoriesDataCallback) {
        apiService.getJokesCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    if (it.isNotEmpty()) {
                        val listCategories: MutableList<String?> = mutableListOf()

                        for (item: CategoriesDataDao in it) {
                            listCategories.add(item.category)
                        }

                        callback.onDataLoaded(listCategories)
                    } else {
                        callback.onNotAvailable()
                    }
                }
            }, {
                callback.onError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getJokesRandomData(
        category: String,
        callback: JokesDataSource.GetJokesRandomDataCallback
    ) {
        apiService.getJokesRandom(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    val jokesData = JokesData(
                        it.icon_url,
                        it.id,
                        it.url,
                        it.value
                    )
                    callback.onDataLoaded(jokesData)
                }
            }, {
                callback.onError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getJokesSearchData(
        query: String,
        callback: JokesDataSource.GetJokesSearchDataCallback
    ) {
        apiService.getJokesSearch(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {
                    val jokesData = JokesData(
                        it.icon_url,
                        it.id,
                        it.url,
                        it.value
                    )
                    callback.onDataLoaded(jokesData)
                }
            }, {
                callback.onError(it.message)
            })
    }
}