package lucas.momo.fixedincome.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import lucas.momo.fixedincome.data.model.AssetDocument
import lucas.momo.fixedincome.domain.repository.AssetRepository

class ObserveAssetsUseCase @Inject constructor(
    private val repository: AssetRepository
) {
    operator fun invoke(): Flow<List<AssetDocument>> = repository.observeAssets()
}
