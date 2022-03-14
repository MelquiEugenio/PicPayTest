package com.picpay.desafio.android

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.DataSaveImplementation
import com.picpay.desafio.android.data.UsersRepositoryImplementation
import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.domain.DataSave
import com.picpay.desafio.android.domain.UsersRepository
import com.picpay.desafio.android.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@HiltAndroidApp
class PicPayTestApplication : Application() {

    @Module
    @InstallIn(ViewModelComponent::class)
    object ContactsModule {

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts =
                    arrayOf<TrustManager>(
                        object : X509TrustManager {
                            @Throws(CertificateException::class)
                            override fun checkClientTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
                            }

                            @Throws(CertificateException::class)
                            override fun checkServerTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                            ) {
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
                val sslSocketFactory: javax.net.ssl.SSLSocketFactory? =
                    sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                if (sslSocketFactory != null) {
                    builder.sslSocketFactory(
                        sslSocketFactory,
                        trustAllCerts[0] as X509TrustManager
                    )
                }
                builder.hostnameVerifier { _, _ -> true }
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        @Provides
        fun providePicPayService(client: OkHttpClient): PicPayService {

            val url = Constants().BASE_URL
            val gson: Gson by lazy { GsonBuilder().create() }

            return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(PicPayService::class.java)
        }

        @Provides
        fun provideUsersRepository(service: PicPayService, dataSave: DataSave): UsersRepository {
            return UsersRepositoryImplementation(service, dataSave)
        }

        @Provides
        fun provideDataSave(@ApplicationContext context: Context): DataSave {
            return DataSaveImplementation(context)
        }
    }
}