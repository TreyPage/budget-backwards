package io.github.treypage.budgetbackwards.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import io.github.treypage.budgetbackwards.model.entity.Income;

@Dao
public interface IncomeDao {

  @Insert
  long insert(Income income);
}
