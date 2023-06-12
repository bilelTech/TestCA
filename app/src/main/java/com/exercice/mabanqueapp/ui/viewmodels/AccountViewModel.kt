package com.exercice.mabanqueapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercice.mabanqueapp.domain.entity.BankAccount
import com.exercice.mabanqueapp.domain.usecases.GetBanksAccountUseCase
import com.exercice.mabanqueapp.utils.Resource
import com.exercice.mabanqueapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getBanksAccountUseCase: GetBanksAccountUseCase,
) : ViewModel() {

    private var _caBankAccountList = MutableLiveData<Resource<List<BankAccount>>>()
    val caBankAccountList: LiveData<Resource<List<BankAccount>>> = _caBankAccountList

    private var _anotherBankAccountList = MutableLiveData<Resource<List<BankAccount>>>()
    val anotherBankAccountList: LiveData<Resource<List<BankAccount>>> = _anotherBankAccountList

    init {
        getBankAccounts()
    }

    private fun getBankAccounts() {
        viewModelScope.launch(Dispatchers.Main) {
            getBanksAccountUseCase().collect { list ->
                if (list.status == Status.Success) {
                    _caBankAccountList.value =
                        Resource.success(list.data?.filter { it.isCA == 1 }?.sortedBy { it.name })
                    _anotherBankAccountList.value =
                        Resource.success(list.data?.filter { it.isCA != 1 }?.sortedBy { it.name })
                }

            }
        }
    }

}