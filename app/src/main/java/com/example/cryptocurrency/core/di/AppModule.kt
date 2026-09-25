package com.example.cryptocurrency.core.di

import com.example.cryptocurrency.core.data.remote.CoinRemoteDataSource
import com.example.cryptocurrency.core.data.repository.CoinRepositoryImpl
import com.example.cryptocurrency.core.domain.repository.CoinRepository
import com.example.cryptocurrency.core.domain.use_cases.GetCoinUseCase
import com.example.cryptocurrency.core.domain.use_cases.GetCoinsUseCase
import com.example.cryptocurrency.core.presentation.coin_detail.CoinDetailViewModel
import com.example.cryptocurrency.core.presentation.coin_list.CoinListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    // 1. تجهيز عميل Ktor (يبقى كما هو لأنه يحتوي على إعدادات مخصصة ولا يمكن استخدام singleOf معه)
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
        }
    }

    // 2. تجهيز الـ Data Source
    singleOf(::CoinRemoteDataSource)

    // 3. تجهيز الـ Repository وربط التنفيذ (Impl) بالواجهة (Interface)
    singleOf(::CoinRepositoryImpl) { bind<CoinRepository>() }

    // 4. تجهيز الـ Use Cases
    factoryOf(::GetCoinsUseCase)
    factoryOf(::GetCoinUseCase)

    viewModelOf(::CoinListViewModel)
    viewModelOf(::CoinDetailViewModel)
}