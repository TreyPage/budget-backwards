package io.github.treypage.budgetbackwards.model.service;

import android.os.AsyncTask;
import edu.cnm.deepdive.android.FluentAsyncTask;
import io.github.treypage.budgetbackwards.BuildConfig;
import io.github.treypage.budgetbackwards.model.entity.Quotes;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface QuotesService {

  @GET()
  Call<String> starWarsQuote(@Url String url);

  Quotes quotes = new Quotes();

  class newQuote extends FluentAsyncTask {

public static void newSwQuote() {

      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BuildConfig.BASE_URL)
          .build();
      QuotesService scalarService = retrofit.create(QuotesService.class);
      Call<String> stringCall = scalarService
          .starWarsQuote("http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote");
      stringCall.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
          if (response.isSuccessful()) {
            String responseString = response.body();
            quotes.SwQuote(responseString);
          }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
      });

//          .baseUrl(BuildConfig.BASE_URL)
//          .client(client)
//          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//          .addConverterFactory(GsonConverterFactory.create())
//          .build();
    }

  }

}
