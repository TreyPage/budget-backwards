package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Income;
import java.util.List;

@Dao
public interface IncomeDao {

  @Insert
  long insert(Income income);

  @Query("SELECT * FROM income")
  LiveData<List<Income>> getAll();
}
