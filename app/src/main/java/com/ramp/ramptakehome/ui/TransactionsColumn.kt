package com.ramp.ramptakehome.ui

import android.util.DisplayMetrics
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ramp.ramptakehome.model.AllSpend
import com.ramp.ramptakehome.utils.dpsAsPixels
import com.ramp.ramptakehome.utils.flattenCurrentIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionsColumn(
    modifier: Modifier,
    state: AllSpend
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val dm = LocalContext.current.resources.displayMetrics
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.surface),
        state = listState
    ) {
        for (day in state.getList().withIndex()) {
            stickyHeader {
                if (day.value.transactions.isNotEmpty()) {
                    DayRow(
                        date = day.value.date,
                        toPreviousDay = { scrollTo(day.index - 1, scope, state, listState, dm) },
                        toNextDay = { scrollTo(day.index + 1, scope, state, listState, dm) },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    )
                }
            }

            items(day.value.transactions) { transaction ->
                TransactionRow(state = transaction)
            }
        }
    }
}

private fun scrollTo(i: Int, scope: CoroutineScope, allSpend: AllSpend, listState: LazyListState, dm: DisplayMetrics) {
    scope.launch {
        val nextIndex = allSpend.flattenCurrentIndex(i)
        listState.scrollToItem(nextIndex,
            100.dpsAsPixels(dm) * (i)
        )
    }
}