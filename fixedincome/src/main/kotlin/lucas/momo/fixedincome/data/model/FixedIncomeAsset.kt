package lucas.momo.fixedincome.data.model

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import java.math.BigDecimal
import java.time.Instant
import lucas.momo.fixedincome.common.toInstantOrNull
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_ACQUISITION_DATE
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_ASSET_PRICE_AT_PURCHASE
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_BROKERAGE
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_INDEXER
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_INTEREST_AND_AMORTIZATION
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_INVESTMENT_AMOUNT
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_MATURITY_DATE
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_NAME
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_STATUS
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_TYPE
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_YIELD
import lucas.momo.fixedincome.data.model.FixedIncomeAsset.Companion.FIELD_YIELD_TYPE

data class FixedIncomeAsset(
    val id: String,
    val acquisitionDate: Instant,
    val assetPriceAtPurchase: BigDecimal,
    val brokerage: String,
    val indexer: String,
    val interestAndAmortization: BigDecimal,
    val investedAmount: BigDecimal,
    val maturityDate: Instant,
    val name: String,
    val status: Boolean,
    val type: String,
    val yield: String,
    val yieldType: String
) {
    companion object {
        const val FIELD_ACQUISITION_DATE = "acquisitionDate"
        const val FIELD_ASSET_PRICE_AT_PURCHASE = "assetPriceAtPurchase"
        const val FIELD_BROKERAGE = "brokerage"
        const val FIELD_INDEXER = "indexer"
        const val FIELD_INTEREST_AND_AMORTIZATION = "interestAndAmortization"
        const val FIELD_INVESTMENT_AMOUNT = "investedAmount"
        const val FIELD_MATURITY_DATE = "maturityDate"
        const val FIELD_NAME = "name"
        const val FIELD_STATUS = "status"
        const val FIELD_TYPE = "type"
        const val FIELD_YIELD = "yield"
        const val FIELD_YIELD_TYPE = "yieldType"
    }
}

fun DocumentSnapshot.toFixedIncomeAsset(): FixedIncomeAsset? {
    return runCatching {
        FixedIncomeAsset(
            id = this.id,
            acquisitionDate = this.getTimestamp(FIELD_ACQUISITION_DATE).toInstantOrNull()
                ?: error(FIELD_ACQUISITION_DATE),
            assetPriceAtPurchase = this.getDouble(FIELD_ASSET_PRICE_AT_PURCHASE)?.toBigDecimal()
                ?: error(FIELD_ASSET_PRICE_AT_PURCHASE),
            brokerage = this.getString(FIELD_BROKERAGE) ?: error(FIELD_BROKERAGE),
            indexer = this.getString(FIELD_INDEXER) ?: error(FIELD_INDEXER),
            interestAndAmortization = this.getDouble(FIELD_INTEREST_AND_AMORTIZATION)
                ?.toBigDecimal() ?: error(FIELD_INTEREST_AND_AMORTIZATION),
            investedAmount = this.getDouble(FIELD_INVESTMENT_AMOUNT)?.toBigDecimal()
                ?: error(FIELD_INVESTMENT_AMOUNT),
            maturityDate = this.getTimestamp(FIELD_MATURITY_DATE).toInstantOrNull()
                ?: error(FIELD_MATURITY_DATE),
            name = this.getString(FIELD_NAME) ?: error(FIELD_NAME),
            status = this.getBoolean(FIELD_STATUS) ?: error(FIELD_STATUS),
            type = this.getString(FIELD_TYPE) ?: error(FIELD_TYPE),
            yield = this.getString(FIELD_YIELD) ?: error(FIELD_YIELD),
            yieldType = this.getString(FIELD_YIELD_TYPE) ?: error(FIELD_YIELD_TYPE)
        )
    }.onFailure { error ->
        Log.e("DocumentSnapshot.toInvestment:", "${error.message}", error)
    }.getOrNull()
}
