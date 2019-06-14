package com.example.threadbarzel.di;

import android.app.Application;
import android.content.Context;

import com.example.threadbarzel.MyThread;
import com.example.threadbarzel.network.Api;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Context provideApplicationContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    @Named("ServerUrl")
    static String provideServerUrl(){
        return "https://jsonplaceholder.typicode.com";
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(@Named("ServerUrl") String serverUrl) {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) No adapter here.
                .build();
    }

    @Singleton
    @Provides
    static Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    static MyThread provideMyThread(Api api){
        return new MyThread(api);
    }

}
