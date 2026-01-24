package lucas.momo.fixedincome.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import lucas.momo.fixedincome.common.Firestore
import lucas.momo.fixedincome.data.model.FixedIncomeAsset
import lucas.momo.fixedincome.data.model.toFixedIncomeAsset
import lucas.momo.fixedincome.domain.repository.InvestmentRepository

class InvestmentRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : InvestmentRepository {

    override fun observeFixedIncomeAssets(): Flow<List<FixedIncomeAsset>> {
        return callbackFlow {
            val subCollectionRef = firestore.collection(Firestore.Collections.INVESTMENTS)
                .document(Firestore.Documents.FIXED_INCOME)
                .collection(Firestore.SubCollections.ASSET)

            val listener = subCollectionRef.addSnapshotListener { snapshot, error ->
                error?.let {
                    close(it)
                    return@addSnapshotListener
                }

                snapshot?.run {
                    val assets = documents.mapNotNull { it.toFixedIncomeAsset() }
                    trySend(assets)
                }
            }
            awaitClose { listener.remove() }
        }
    }
}
