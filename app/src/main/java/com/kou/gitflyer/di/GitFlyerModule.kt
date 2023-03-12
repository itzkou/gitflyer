package com.kou.gitflyer.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kou.gitflyer.data.prefs.PrefsStore
import com.kou.gitflyer.data.remote.GitFlyerAbstraction
import com.kou.gitflyer.data.remote.GitFlyerRemote
import com.kou.gitflyer.data.repo.GitFlyerRepo
import com.kou.gitflyer.data.repo.GitFlyerRepoAbstraction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GitFlyerModule {

    /**** Retrofit  ******/
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl( "https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    /**** Remote ******/
    @Provides
    fun provideFisaaApi(retrofit: Retrofit): GitFlyerAbstraction = retrofit.create(GitFlyerAbstraction::class.java)


    /** DataStore **/
    @Provides
    @Singleton
    fun providePrefsStoreAbstraction(@ApplicationContext appContext: Context) =
        PrefsStore(appContext)


    /**** main Repo ******/
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Provides
    @Singleton
    fun provideGitFlyerRepoAbstraction(
        remote: GitFlyerRemote,
        ioDispatcher: CoroutineDispatcher

    ): GitFlyerRepoAbstraction =
        GitFlyerRepo(
            remote,
            ioDispatcher
        )


}