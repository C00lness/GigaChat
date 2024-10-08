package com.example.gigachat.Net

import android.app.Application
import androidx.room.Room
import com.example.gigachat.DataBase.AnswerDataBase
import com.example.gigachat.DataBase.RegistrationDataBase
import com.example.gigachat.Repository.ADataBase
import com.example.gigachat.Repository.RDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClasses {

    @Provides
    fun providesBaseUrl() = "https://ngw.devices.sberbank.ru:9443/api/v2/"

    @Provides
    @Singleton
    fun getRetrofitInstance(base_Url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_Url)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofitInterface(retrofit: Retrofit): RetrofitServices {
        return retrofit.create(RetrofitServices::class.java)
    }
}


fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }
        builder
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}