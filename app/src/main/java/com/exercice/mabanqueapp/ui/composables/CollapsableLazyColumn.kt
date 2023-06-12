package com.exercice.mabanqueapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exercice.mabanqueapp.AccountDetailScreen
import com.exercice.mabanqueapp.R
import com.exercice.mabanqueapp.domain.entity.Account


@Composable
fun CollapsableLazyColumn(
    sections: List<CollapsableSection>,
    modifier: Modifier = Modifier,
    goToAccountDetails: (Account) -> Unit
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        }
                ) {
                    Text(
                        dataItem.title,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.black),
                        modifier = Modifier
                            .padding(30.dp, 10.dp, 0.dp, 10.dp)
                            .weight(1f)
                    )
                    Text(
                        "${dataItem.price} €",
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.grey_more_light),
                        fontSize = 14.sp
                    )
                    Icon(
                        Icons.Default.run {
                            if (collapsed)
                                KeyboardArrowDown
                            else
                                KeyboardArrowUp
                        },
                        contentDescription = "",
                        tint = Color.LightGray,
                    )
                }
                Divider(
                    color = colorResource(id = R.color.divider_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
            if (!collapsed) {
                items(dataItem.rows) { row ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                goToAccountDetails.invoke(row)
                            }
                    ) {
                        Spacer(
                            modifier = Modifier.size(MaterialIconDimension.dp)
                        )
                        Text(
                            row.label,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .weight(1f)
                        )
                        Text(
                            "${row.balance} €",
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.grey_more_light),
                            fontSize = 14.sp
                        )
                        Icon(
                            Icons.Default.run {
                                KeyboardArrowRight
                            },
                            contentDescription = "",
                            tint = Color.LightGray,
                        )
                    }
                    Divider(
                        color = colorResource(id = R.color.divider_color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

data class CollapsableSection(
    val title: String,
    val price: String,
    val isCA: Int,
    val rows: List<Account>
)

const val MaterialIconDimension = 24f