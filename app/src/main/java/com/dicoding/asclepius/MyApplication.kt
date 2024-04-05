package com.dicoding.asclepius

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(getInjectionModule())
        }
    }

    private fun getInjectionModule(): Module {
        return module {
            single { ApiConfig.getApiService(get()) }
            single { ArticleRepository(get()) }
            viewModel { NewsViewModel(get()) }
        }
    }
}