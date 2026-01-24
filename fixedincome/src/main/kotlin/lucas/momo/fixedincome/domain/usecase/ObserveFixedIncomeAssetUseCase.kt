package lucas.momo.fixedincome.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import lucas.momo.fixedincome.data.model.FixedIncomeAsset
import lucas.momo.fixedincome.domain.repository.InvestmentRepository

class ObserveFixedIncomeAssetUseCase @Inject constructor(
    private val repository: InvestmentRepository
) {
    operator fun invoke(): Flow<List<FixedIncomeAsset>> = repository.observeFixedIncomeAssets()
}
