package com.example.bibzpole.moduleDi

import android.content.Context
import android.content.SharedPreferences
import com.example.bibzpole.Interfaces.MusicApi
import com.example.bibzpole.database.Appdatbase
import com.example.bibzpole.database.SaveFavDao
import com.example.bibzpole.database.USerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DependencyInjection {


    @Singleton
    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun musicApi(): MusicApi {
        return MusicApi.create()
    }
    @Singleton
    @Provides
    fun getSaveFavDao(appDatabase: Appdatbase): SaveFavDao {
        return appDatabase.savefavDao()
    }
    @Singleton
    @Provides
    fun getLoginDao(appDatabase: Appdatbase): USerDao {
        return appDatabase.login()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Appdatbase {
        return Appdatbase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }
}