package com.exercice.mabanqueapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercice.mabanqueapp.domain.entity.Account
import com.exercice.mabanqueapp.domain.usecases.GetAccountDetailsUseCase
import com.exercice.mabanqueapp.utils.Resource
import com.exercice.mabanqueapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountDetailsViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
) : ViewModel() {

    private var _account = MutableLiveData<Resource<Account>>()
    val account: LiveData<Resource<Account>> = _account




    fun getAccountsDetails(accountId: String) {
        viewModelScope.launch(Dispatchers.Main) {
            getAccountDetailsUseCase(accountId).collect { account ->
                if (account.status == Status.Success) {
                    val list = account.data?.operations?.sortedBy { it.title }?.sortedByDescending { it.date }
                    _account.value = Resource.success(
                        Account(
                            account.data?.balance ?: 0.0,
                            account.data?.id ?: "",
                            account.data?.label ?: "",
                            list ?: emptyList()
                        )
                    )
                }
            }
        }
    }

}