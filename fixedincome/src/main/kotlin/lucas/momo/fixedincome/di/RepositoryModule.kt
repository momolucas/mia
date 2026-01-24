package lucas.momo.fixedincome.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lucas.momo.fixedincome.data.repository.InvestmentRepositoryImpl
import lucas.momo.fixedincome.domain.repository.InvestmentRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFixedIncomeRepository(impl: InvestmentRepositoryImpl): InvestmentRepository
}
