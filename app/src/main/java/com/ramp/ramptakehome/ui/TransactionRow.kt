package com.ramp.ramptakehome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramp.ramptakehome.R
import com.ramp.ramptakehome.model.Transaction
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TransactionRow(
    state: Transaction,
    modifier: Modifier = Modifier
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
    content = {
        Image(
            painter = painterResource(id = R.drawable.ramp_logo),
            contentDescription = null,
            modifier = Modifier.height(40.dp).width(40.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.wrapContentHeight().weight(1f)) {
            val textModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp)
            Text(
                text = state.merchantName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                modifier = textModifier
            )
            Text(
                text = state.merchantCategory,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = textModifier
            )
        }
        Text(
            text = NumberFormat.getCurrencyInstance(Locale.US).format(state.amount),
            modifier = Modifier.wrapContentSize(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
)