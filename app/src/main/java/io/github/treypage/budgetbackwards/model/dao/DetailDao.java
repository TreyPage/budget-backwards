package io.github.treypage.budgetbackwards.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.github.treypage.budgetbackwards.model.entity.Detail;
import java.util.List;

@Dao
public interface DetailDao {

  @Insert
  long insert(Detail detail);

  @Query("SELECT * FROM detail")
  LiveData<List<Detail>> getAll();
}
