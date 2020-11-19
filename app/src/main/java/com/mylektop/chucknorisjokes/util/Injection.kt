package com.mylektop.chucknorisjokes.util

import android.content.Context
import android.preference.PreferenceManager
import com.mylektop.chucknorisjokes.data.source.JokesDataRepository
import com.mylektop.chucknorisjokes.data.source.local.JokesDataLocalSource
import com.mylektop.chucknorisjokes.data.source.remote.JokesDataRemoteSource

/**
 * Created by iddangunawan on 11/18/20
 */
object Injection {
    fun providedMainDataRepository(context: Context) = JokesDataRepository.getInstance(
        JokesDataRemoteSource,
        JokesDataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context))!!
    )
}