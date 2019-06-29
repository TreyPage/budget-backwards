package io.github.treypage.budgetbackwards.model.service;

import io.github.treypage.budgetbackwards.BuildConfig;
import io.github.treypage.budgetbackwards.model.entity.Quotes;
import io.reactivex.Single;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface QuotesService {

  @GET("RandomStarWarsQuote")
  Single<String> starWarsQuote();


  static QuotesService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final QuotesService INSTANCE;

    static {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .client(client)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(QuotesService.class);
    }

  }

}
