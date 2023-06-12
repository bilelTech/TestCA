package com.exercice.mabanqueapp.data.remote

import com.exercice.mabanqueapp.data.entity.GetAccountsResponse
import retrofit2.Response
import retrofit2.http.GET

interface MaBanqueApi {

    @GET("banks.json")
    suspend fun getBankAccounts() : Response<GetAccountsResponse>
}