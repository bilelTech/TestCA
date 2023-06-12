package com.exercice.mabanqueapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercice.mabanqueapp.R
import com.exercice.mabanqueapp.domain.entity.Operation
import com.exercice.mabanqueapp.utils.Utils

@Preview(showBackground = true)
@Composable
fun ListLazyColumn(
    operations: List<Operation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.background(colorResource(id = R.color.white))) {
        operations.forEachIndexed { i, dataItem ->
            item(key = "header_$i") {
                Row(modifier= Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .padding(30.dp, 0.dp, 0.dp, 30.dp)
                            .weight(1.0f)
                    ) {
                        Text(
                            dataItem.title,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.greyy_color),
                        )
                        Text(
                            "${Utils.getDate(dataItem.date ?: "")}",
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.greyy_color),
                            fontSize = 14.sp
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "${dataItem.amount} €",
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.greyy_color),
                            fontSize = 14.sp
                        )
                    }
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