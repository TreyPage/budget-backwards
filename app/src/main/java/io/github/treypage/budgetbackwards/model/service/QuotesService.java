package io.github.treypage.budgetbackwards.model.service;

import androidx.annotation.Nullable;
import edu.cnm.deepdive.android.FluentAsyncTask;
import io.github.treypage.budgetbackwards.BuildConfig;
import io.github.treypage.budgetbackwards.model.entity.Quote;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface QuotesService {

  @GET("RandomStarWarsQuote")
  Call<Quote> starWarsQuote();

  class InstanceHolder {

    private static final QuotesService INSTANCE;

    static {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(QuotesService.class);
    }
  }

  class NewQuote extends FluentAsyncTask<Void, Void, Quote> {

    @Nullable
    @Override
    protected Quote perform(Void... nothing) {
      try {
        return InstanceHolder.INSTANCE.starWarsQuote().execute().body();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }
}
