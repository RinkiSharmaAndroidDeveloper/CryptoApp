package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.comman.Constant
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPeperikaApi
import com.plcoding.cryptocurrencyappyt.data.repository.CoinRepositoryImp
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePeperikaApi(): CoinPeperikaApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPeperikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPeperikaApi): CoinRepository {
        return CoinRepositoryImp(api)
    }
}