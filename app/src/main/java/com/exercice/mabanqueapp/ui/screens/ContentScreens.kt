package com.exercice.mabanqueapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exercice.mabanqueapp.domain.entity.Account
import com.exercice.mabanqueapp.ui.composables.CollapsableLazyColumn
import com.exercice.mabanqueapp.ui.composables.CollapsableSection
import com.exercice.mabanqueapp.ui.composables.ListLazyColumn
import com.exercice.mabanqueapp.ui.viewmodels.AccountDetailsViewModel
import com.exercice.mabanqueapp.ui.viewmodels.AccountViewModel

@Preview(showBackground = true)
@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel,
    goToAccountDetails: (Account) -> Unit,
) {

    val caBanks = accountViewModel.caBankAccountList.observeAsState()
    val anotherBanks = accountViewModel.anotherBankAccountList.observeAsState()
    val list = ArrayList<CollapsableSection>()
    val anotherBanksList = ArrayList<CollapsableSection>()
    caBanks.value?.data?.map {
        list.add(CollapsableSection(it.name, it.balance.toString(), it.isCA, it.accounts))
    }
    anotherBanks.value?.data?.map {
        anotherBanksList.add(
            CollapsableSection(
                it.name,
                it.balance.toString(),
                it.isCA,
                it.accounts
            )
        )
    }

    //Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    id = R.color.grey
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.account_screen_title),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(id = R.string.account_screen_credit_agricole),
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.grey_light),
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.size(10.dp))
        }

        CollapsableLazyColumn(
            sections = list,
            modifier = Modifier
                .background(
                    colorResource(id = R.color.white)
                )
                .fillMaxWidth(1f)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            goToAccountDetails
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.account_screen_another_account),
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.grey_light),
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.size(10.dp))

        CollapsableLazyColumn(
            sections = anotherBanksList,
            modifier = Modifier
                .background(
                    colorResource(id = R.color.white)
                )
                .fillMaxWidth(1f)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            goToAccountDetails
        )

    }

}

@Composable
fun SelectedScreen(item: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item, fontSize = 35.sp)
    }
}

@Composable
fun AccountDetailScreen(
    accountDetailsViewModel: AccountDetailsViewModel,
    accountId: String,
    onBackPressed: () -> Unit
) {

    accountDetailsViewModel.getAccountsDetails(accountId)
    val account = accountDetailsViewModel.account.observeAsState().value?.data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    id = R.color.grey
                )
            )
    ) {
        Column() {
            Spacer(modifier = Modifier.size(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .clickable {
                    onBackPressed()
                }) {

                Icon(
                    Icons.Default.run {
                        KeyboardArrowLeft
                    },
                    contentDescription = "",
                    tint = Color.LightGray,
                )
                Text(
                    text = stringResource(id = R.string.item_nav_account),
                    color = colorResource(id = R.color.purple_200),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${account?.balance ?: ""} €",
                    color = colorResource(id = R.color.greyy_color),
                    fontWeight = FontWeight.Normal,
                    fontSize = 35.sp,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = account?.label ?: "",
                    color = colorResource(id = R.color.greyy_color),
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp
                )
            }
            ListLazyColumn(
                operations = account?.operations ?: emptyList(),
                Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .background(
                        colorResource(id = R.color.white)
                    )
            )

        }
    }
}