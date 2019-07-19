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
package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import java.util.List;

@Dao
public interface ExpenseDao {

  @Insert
  long insert(Expense expense);

  @Delete
  int delete(Expense title);

  @Delete
  int delete(Expense... expenses);

  @Query("SELECT * FROM expense")
  LiveData<List<Expense>> getAll();

  @Query("SELECT SUM(amount) FROM expense")
  LiveData<Long> getSumExpenses();

  @Query("SELECT 100.0 * SUM(IFNULL(ex.amount, 0))/t.total FROM category c left join expense ex ON ex.category_id = c.id CROSS JOIN (SELECT SUM(amount) AS total FROM Expense) t GROUP BY c.id")
  List<Double> getPercent();
}
