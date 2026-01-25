package lucas.momo.fixedincome.common

import com.google.firebase.Timestamp
import java.time.Instant

fun Timestamp?.toInstantOrNull(): Instant? =
    this?.let { Instant.ofEpochSecond(it.seconds, it.nanoseconds.toLong()) }
