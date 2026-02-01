package lucas.momo.fixedincome.domain.repository

import kotlinx.coroutines.flow.Flow
import lucas.momo.fixedincome.data.model.AssetDocument

interface AssetRepository {
    fun observeAssets(): Flow<List<AssetDocument>>
}
