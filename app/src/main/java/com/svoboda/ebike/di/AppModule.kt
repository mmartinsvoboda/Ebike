package com.svoboda.ebike.di

import com.svoboda.ebike.data.local.AppDatabase
import com.svoboda.ebike.data.local.AppDatabaseCallback
import com.svoboda.ebike.data.repository.BikeRepositoryImpl
import com.svoboda.ebike.data.repository.WheelSizeRepositoryImpl
import com.svoboda.ebike.domain.repository.BikeRepository
import com.svoboda.ebike.domain.repository.WheelSizeRepository
import com.svoboda.ebike.domain.usecase.CalculateSpeedUseCase
import com.svoboda.ebike.domain.usecase.InsertAllBikesUseCase
import com.svoboda.ebike.domain.usecase.InsertAllWheelSizesUseCase
import com.svoboda.ebike.domain.usecase.ObserveAllBikesUseCase
import com.svoboda.ebike.domain.usecase.ObserveAllWheelSizesUseCase
import com.svoboda.ebike.domain.usecase.ParseBikesUseCase
import com.svoboda.ebike.domain.usecase.ParseWheelSizesUseCase
import com.svoboda.ebike.presentation.viewmodel.BikeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get(), get(), get()) }
    factory { AppDatabaseCallback(get(), get(), get()) }
    factory { get<AppDatabase>().bikeDao() }
    factory { get<AppDatabase>().wheelSizeDao() }
    factory<BikeRepository> { BikeRepositoryImpl(get()) }
    factory<WheelSizeRepository> { WheelSizeRepositoryImpl(get()) }
    factory { ParseBikesUseCase(get()) }
    factory { ParseWheelSizesUseCase(get()) }
    factory { ObserveAllBikesUseCase(get()) }
    factory { ObserveAllWheelSizesUseCase(get()) }
    factory { InsertAllBikesUseCase(get()) }
    factory { InsertAllWheelSizesUseCase(get()) }
    factory { CalculateSpeedUseCase() }
    viewModel { BikeViewModel(get(), get(), get()) }
}
