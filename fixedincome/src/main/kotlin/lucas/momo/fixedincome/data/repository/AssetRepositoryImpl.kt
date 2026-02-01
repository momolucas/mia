package lucas.momo.fixedincome.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import lucas.momo.fixedincome.common.Firestore
import lucas.momo.fixedincome.data.model.AssetDocument
import lucas.momo.fixedincome.data.model.toAssetDocument
import lucas.momo.fixedincome.domain.repository.AssetRepository

class AssetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : AssetRepository {

    override fun observeAssets(): Flow<List<AssetDocument>> {
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
                    val assets = documents.mapNotNull { it.toAssetDocument() }
                    trySend(assets)
                }
            }
            awaitClose { listener.remove() }
        }
    }
}
