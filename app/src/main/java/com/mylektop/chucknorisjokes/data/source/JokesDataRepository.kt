package com.mylektop.chucknorisjokes.data.source

import com.mylektop.chucknorisjokes.data.JokesData
import com.mylektop.chucknorisjokes.data.source.local.JokesDataLocalSource

/**
 * Created by iddangunawan on 11/18/20
 */
class JokesDataRepository(
    val remoteDataSource: JokesDataSource,
    val localDataSource: JokesDataSource
) : JokesDataSource {

    override fun getJokesCategoriesData(callback: JokesDataSource.GetJokesCategoriesDataCallback) {
        remoteDataSource.getJokesCategoriesData(object :
            JokesDataSource.GetJokesCategoriesDataCallback {
            override fun onDataLoaded(categoriesData: MutableList<String?>) {
                callback.onDataLoaded(categoriesData)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }
        })
    }

    override fun getJokesRandomData(
        category: String,
        callback: JokesDataSource.GetJokesRandomDataCallback
    ) {
        remoteDataSource.getJokesRandomData(
            category,
            object : JokesDataSource.GetJokesRandomDataCallback {
                override fun onDataLoaded(jokesData: JokesData?) {
                    callback.onDataLoaded(jokesData)
                }

                override fun onNotAvailable() {
                    callback.onNotAvailable()
                }

                override fun onError(msg: String?) {
                    callback.onError(msg)
                }
            })
    }

    override fun getJokesSearchData(
        query: String,
        callback: JokesDataSource.GetJokesSearchDataCallback
    ) {
        remoteDataSource.getJokesSearchData(
            query,
            object : JokesDataSource.GetJokesSearchDataCallback {
                override fun onDataLoaded(jokesData: JokesData?) {
                    callback.onDataLoaded(jokesData)
                }

                override fun onNotAvailable() {
                    callback.onNotAvailable()
                }

                override fun onError(msg: String?) {
                    callback.onError(msg)
                }
            })
    }

    companion object {
        private var INSTANCE: JokesDataRepository? = null

        @JvmStatic
        fun getInstance(
            jokesDataRemoteSource: JokesDataSource,
            newsLocalDataSource: JokesDataLocalSource
        ) = INSTANCE ?: synchronized(JokesDataRepository::class.java) {
            INSTANCE ?: JokesDataRepository(
                jokesDataRemoteSource,
                jokesDataRemoteSource
            ).also { INSTANCE = it }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}