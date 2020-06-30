package com.example.myapplication.util

import com.amazonaws.mobileconnectors.appsync.AppSyncQueryCall
import com.apollographql.apollo.GraphQLCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException


fun <T> AppSyncQueryCall<T>.enqueue(success: (Response<T>) -> Unit, failure: (ApolloException) -> Unit) {
    this.enqueue(object : GraphQLCall.Callback<T>() {
        override fun onFailure(e: ApolloException) {
            failure(e)
        }

        override fun onResponse(response: Response<T>) {
            success(response)
        }
    })
}

