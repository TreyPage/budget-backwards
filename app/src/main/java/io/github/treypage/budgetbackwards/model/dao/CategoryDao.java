package io.github.treypage.budgetbackwards.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import io.github.treypage.budgetbackwards.model.entity.Category;

@Dao
public interface CategoryDao {

  @Insert
  long insert(Category category);
}
