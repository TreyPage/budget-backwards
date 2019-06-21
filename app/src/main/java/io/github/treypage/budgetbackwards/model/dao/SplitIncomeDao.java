package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.SplitIncome;
import java.util.List;

@Dao
public interface SplitIncomeDao {

  @Insert
  long insert(SplitIncome splitIncome);

  @Query("SELECT * FROM splitIncome")
  LiveData<List<SplitIncome>> getAll();
}
