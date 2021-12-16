package com.example.gift.infrastructure.retrofit

import com.example.gift.common.response.CommonResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


@Component
class RetrofitUtils {
    companion object {
        @JvmStatic
        private val loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        @JvmStatic
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        @JvmStatic
        private val gson: Gson = GsonBuilder().setLenient().create()

        @JvmStatic
        fun initRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        }
    }


    fun <T : CommonResponse<*>> responseSync(call: Call<T>): T? {
        var body: T? = null;
        try {
            val execute = call.execute()
            if (execute.isSuccessful) {
                body = execute.body()
            } else {
                println("requestSync errorBody = ${execute.errorBody()}")
                throw RuntimeException("retrofit execute response error")
            }
        } catch (e: IOException) {
            println(e)
            throw RuntimeException("retrofit execute IOException")
        }

        return body!!
    }

    fun responseVoid(call: Call<Void>) {
        try {
            if (!call.execute().isSuccessful) throw RuntimeException()
        } catch (e: IOException) {
            throw RuntimeException()
        }
    }

}