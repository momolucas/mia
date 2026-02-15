package lucas.momo.fixedincome.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lucas.momo.fixedincome.data.repository.AssetRepositoryImpl
import lucas.momo.fixedincome.domain.repository.AssetRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAssetRepository(impl: AssetRepositoryImpl): AssetRepository
}
