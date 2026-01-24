package lucas.momo.fixedincome.domain.repository

import kotlinx.coroutines.flow.Flow
import lucas.momo.fixedincome.data.model.FixedIncomeAsset

interface InvestmentRepository {
    fun observeFixedIncomeAssets(): Flow<List<FixedIncomeAsset>>
}
