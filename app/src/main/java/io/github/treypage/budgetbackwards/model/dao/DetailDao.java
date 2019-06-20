package io.github.treypage.budgetbackwards.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import io.github.treypage.budgetbackwards.model.entity.Detail;

@Dao
public interface DetailDao {

  @Insert
  long insert(Detail detail);
}
