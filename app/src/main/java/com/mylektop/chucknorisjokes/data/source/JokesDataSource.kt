package com.mylektop.chucknorisjokes.data.source

import com.mylektop.chucknorisjokes.data.JokesData

/**
 * Created by iddangunawan on 11/18/20
 */
interface JokesDataSource {
    fun getJokesCategoriesData(callback: GetJokesCategoriesDataCallback)
    fun getJokesRandomData(category: String, callback: GetJokesRandomDataCallback)
    fun getJokesSearchData(query: String, callback: GetJokesSearchDataCallback)

    interface GetJokesCategoriesDataCallback {
        fun onDataLoaded(categoriesData: MutableList<String?>)
        fun onNotAvailable()
        fun onError(msg: String?)
    }

    interface GetJokesRandomDataCallback {
        fun onDataLoaded(jokesData: JokesData?)
        fun onNotAvailable()
        fun onError(msg: String?)
    }

    interface GetJokesSearchDataCallback {
        fun onDataLoaded(jokesData: JokesData?)
        fun onNotAvailable()
        fun onError(msg: String?)
    }
}