package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Category;
import java.util.List;

@Dao
public interface CategoryDao {

  @Insert
  long insert(Category category);

  @Query("SELECT * FROM category")
  LiveData<List<Category>> getAll();

  @Query("SELECT * FROM category WHERE id = :id")
  LiveData<Category> findById(Long id);
}
