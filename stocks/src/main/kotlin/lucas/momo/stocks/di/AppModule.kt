package lucas.momo.stocks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import lucas.momo.stocks.data.network.TwelveDataApi
import lucas.momo.stocks.data.repositories.StockExchangeRepository
import lucas.momo.stocks.data.repositories.StockExchangeRepositoryImpl
import lucas.momo.stocks.domain.usecases.GetStockExchangeUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTwelveDataApi(): TwelveDataApi = TwelveDataApi()

    @Provides
    @Singleton
    fun provideStockExchangeRepository(api: TwelveDataApi): StockExchangeRepository {
        return StockExchangeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetStockExchangeUseCase(
        repository: StockExchangeRepository
    ): GetStockExchangeUseCase {
        return GetStockExchangeUseCase(repository)
    }
}
