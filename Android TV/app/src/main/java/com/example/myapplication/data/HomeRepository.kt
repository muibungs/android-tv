package com.example.myapplication.data

import com.amazonaws.amplify.generated.graphql.*
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.myapplication.util.RateLimiter
import com.example.myapplication.util.enqueue
import java.util.concurrent.TimeUnit

interface HomeRepository {
    fun getHome(
        query: GetHomeQuery,
        isForceFetch: Boolean,
        response: (Response<GetHomeQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    )

    fun getDrama(
        query: GetDramaQuery,
        isForceFetch: Boolean,
        response: (Response<GetDramaQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    )

    fun getMovie(
        query: GetMovieQuery,
        isForceFetch: Boolean,
        response: (Response<GetMovieQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    )
}

class HomeRepositoryImpl(private val awsAppSyncClient: AWSAppSyncClient) : HomeRepository {

    private val rateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)
    private val contentsRateLimiter = RateLimiter<ContentsQuery.Variables>(1, TimeUnit.HOURS)
    private val categoriesRateLimiter = RateLimiter<CategoriesQuery.Variables>(1, TimeUnit.HOURS)
    private val finExRateLimiter = RateLimiter<GetFinexCategoriesQuery.Variables>(1, TimeUnit.HOURS)

    override fun getHome(
        query: GetHomeQuery,
        isForceFetch: Boolean,
        response: (Response<GetHomeQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    ) {
        val fetchMode = AppSyncResponseFetchers.NETWORK_ONLY

        awsAppSyncClient.query(query)
            .responseFetcher(fetchMode)
            .enqueue({
                if (it.hasErrors()) {
                    rateLimiter.reset("HOME")
                }
                response(it)
            }, {
                rateLimiter.reset("HOME")
                failure(it)
            })
    }

    override fun getDrama(
        query: GetDramaQuery,
        isForceFetch: Boolean,
        response: (Response<GetDramaQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    ) {
        val fetchMode = AppSyncResponseFetchers.NETWORK_ONLY

        awsAppSyncClient.query(query)
            .responseFetcher(fetchMode)
            .enqueue({
                if (it.hasErrors()) {
                    rateLimiter.reset("HOME")
                }
                response(it)
            }, {
                rateLimiter.reset("HOME")
                failure(it)
            })
    }

    override fun getMovie(
        query: GetMovieQuery,
        isForceFetch: Boolean,
        response: (Response<GetMovieQuery.Data?>) -> Unit,
        failure: (ApolloException) -> Unit
    ) {
        val fetchMode = AppSyncResponseFetchers.NETWORK_ONLY

        awsAppSyncClient.query(query)
            .responseFetcher(fetchMode)
            .enqueue({
                if (it.hasErrors()) {
                    rateLimiter.reset("HOME")
                }
                response(it)
            }, {
                rateLimiter.reset("HOME")
                failure(it)
            })
    }

}