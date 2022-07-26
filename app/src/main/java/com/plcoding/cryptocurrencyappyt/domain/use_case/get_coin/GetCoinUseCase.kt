package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin

import com.plcoding.cryptocurrencyappyt.comman.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetail

import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail

import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {
operator fun invoke(coinId:String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
           val coin = repository.getCoinById(coinId).toCoinDetail()
           emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
          emit(Resource.Error<CoinDetail>(e.localizedMessage ?:"An unexpected error occur"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Can't reach to server. Please check your internet"))
        }

    }
}