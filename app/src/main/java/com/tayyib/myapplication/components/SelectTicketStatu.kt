package com.tayyib.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tayyib.myapplication.models.ticket_status.TicketStatusResponseItem
import com.tayyib.myapplication.ui.theme.bimserColor


@Composable
fun SelectTicketStatu(
    statu: TicketStatusResponseItem,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelectionChange(!isSelected) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onSelectionChange(it) },
           
            colors = CheckboxDefaults.colors(
                checkedColor = bimserColor
            )
        )
        Text(
            text = statu.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}