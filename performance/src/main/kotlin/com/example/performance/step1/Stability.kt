package com.example.performance.step1

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Components Compose considers as stable:
 * - primitive vals
 * - immutable collections
 * - objects containing only stable parameters
 *
 * Components Compose considers unstable:
 * - mutable or collections
 * - data from other modules, in which Compose compiler did not run (mostly data and domain modules)
 *
 * We can force Compose to consider data as stable or immutable, Compose will not validate it
 */

/**
 * We mark classes with collections as immutable only when we are sure the implementation of collection is immutable
 * or the data will not change
 */
@Immutable
data class ForceImmutableData(
    val list: List<String>
)

class UnmarkedStableData {
    val text = MutableStateFlow("text")
}

@Stable
/**
 * In this case, we uphold the contract and we can mark the class as Stable
 * Compose will know about all changes in all fields of this class (when we subscribe to them using .collectAsState() method)
 */
class ForcedStableData {
    val text = MutableStateFlow("text")
}

data class UnstableData(
    val list: List<String>
)