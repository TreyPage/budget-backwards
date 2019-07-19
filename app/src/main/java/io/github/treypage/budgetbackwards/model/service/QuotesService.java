//    MIT License
//
//    Copyright (c) 2019 Terrell Page
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
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
