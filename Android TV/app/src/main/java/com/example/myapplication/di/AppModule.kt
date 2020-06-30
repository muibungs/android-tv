package com.example.myapplication.di

import android.content.Context
import com.amazonaws.mobile.config.AWSConfiguration
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient
import com.example.myapplication.data.HomeRepository
import com.example.myapplication.data.HomeRepositoryImpl
import com.example.myapplication.domain.home.GetDramaUseCase
import com.example.myapplication.domain.home.GetHomesUseCase
import com.example.myapplication.domain.home.GetMovieUseCase
import com.example.myapplication.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get(), get())
    }
}

val domainModule = module {
    factory { GetHomesUseCase(get()) }
    factory { GetDramaUseCase(get()) }
    factory { GetMovieUseCase(get()) }

}

val dataModule = module {
    single(named("Bugaboo")) { getAwsBugaboo(androidContext()) }
    single<HomeRepository> {
        HomeRepositoryImpl(
            awsAppSyncClient = get(named("Bugaboo"))
        )
    }

}

private fun getAwsBugaboo(context: Context): AWSAppSyncClient {

    val trustAllCerts = arrayOf<X509TrustManager>(
        object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<X509Certificate?>?,
                authType: String?
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<X509Certificate?>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                return arrayOf()
            }
        }
    )

    // Install the all-trusting trust manager
    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustAllCerts, SecureRandom())
    // Create an ssl socket factory with our all-trusting manager
    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory

    return AWSAppSyncClient.builder()
        .context(context)
        .awsConfiguration(AWSConfiguration(context).apply {
            configuration = "bugaboobff-dev"
        })
        .useClientDatabasePrefix(true)
        .okHttpClient(
            OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0])
                .build()
        )
        .build()
}