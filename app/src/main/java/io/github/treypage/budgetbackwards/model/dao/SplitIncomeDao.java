package io.github.treypage.budgetbackwards.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import io.github.treypage.budgetbackwards.model.entity.SplitIncome;

@Dao
public interface SplitIncomeDao {

  @Insert
  long insert(SplitIncome splitIncome);
}
