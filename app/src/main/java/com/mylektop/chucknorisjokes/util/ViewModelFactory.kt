package com.mylektop.chucknorisjokes.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mylektop.chucknorisjokes.data.source.JokesDataRepository
import com.mylektop.chucknorisjokes.categories.CategoriesViewModel

/**
 * Created by iddangunawan on 11/18/20
 */
class ViewModelFactory private constructor(
    private val application: Application,
    private val mainDataRepository: JokesDataRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(CategoriesViewModel::class.java) ->
                CategoriesViewModel(application, mainDataRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    application,
                    Injection.providedMainDataRepository(application.applicationContext)
                ).also { INSTANCE = it }
            }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}