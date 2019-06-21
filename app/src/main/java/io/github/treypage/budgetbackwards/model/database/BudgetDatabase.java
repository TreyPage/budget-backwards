package io.github.treypage.budgetbackwards.model.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import io.github.treypage.budgetbackwards.model.dao.CategoryDao;
import io.github.treypage.budgetbackwards.model.dao.DetailDao;
import io.github.treypage.budgetbackwards.model.dao.IncomeDao;
import io.github.treypage.budgetbackwards.model.dao.SplitIncomeDao;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Detail;
import io.github.treypage.budgetbackwards.model.entity.Income;
import io.github.treypage.budgetbackwards.model.entity.SplitIncome;

@Database(entities = {Income.class, Category.class, Detail.class, SplitIncome.class}, version = 1,exportSchema = true)

public abstract class BudgetDatabase extends RoomDatabase {

  public abstract CategoryDao getCategoryDao();
  public abstract IncomeDao getIncomeDao();
  public abstract DetailDao getDetailDao();
  public abstract SplitIncomeDao getSplitIncomeDao();

  private static BudgetDatabase INSTANCE;

  public static BudgetDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room
          .databaseBuilder(context.getApplicationContext(), BudgetDatabase.class, "income_room")
          .build();
    }
    return INSTANCE;
  }
}
