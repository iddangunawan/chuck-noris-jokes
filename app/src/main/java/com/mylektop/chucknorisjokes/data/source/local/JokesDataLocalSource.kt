package com.mylektop.chucknorisjokes.data.source.local

import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.mylektop.chucknorisjokes.data.source.JokesDataSource

/**
 * Created by iddangunawan on 11/18/20
 */
class JokesDataLocalSource private constructor(private val preferences: SharedPreferences) :
    JokesDataSource {
    override fun getJokesCategoriesData(callback: JokesDataSource.GetJokesCategoriesDataCallback) {

    }

    override fun getJokesRandomData(
        category: String,
        callback: JokesDataSource.GetJokesRandomDataCallback
    ) {

    }

    override fun getJokesSearchData(
        query: String,
        callback: JokesDataSource.GetJokesSearchDataCallback
    ) {

    }

    companion object {
        private var INSTANCE: JokesDataLocalSource? = null

        @JvmStatic
        fun getInstance(preferences: SharedPreferences): JokesDataLocalSource? {
            if (INSTANCE == null) {
                synchronized(JokesDataLocalSource::class.java) {
                    INSTANCE = JokesDataLocalSource(preferences)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}