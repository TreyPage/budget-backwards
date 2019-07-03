package io.github.treypage.budgetbackwards.model.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.github.treypage.budgetbackwards.model.dao.CategoryDao;
import io.github.treypage.budgetbackwards.model.dao.ExpenseDao;
import io.github.treypage.budgetbackwards.model.dao.IncomeDao;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.model.entity.Income;
import java.util.concurrent.Executors;

@Database(entities = {Income.class, Category.class, Expense.class}, version = 1, exportSchema = true)

public abstract class BudgetDatabase extends RoomDatabase {

  public abstract CategoryDao getCategoryDao();

  public abstract IncomeDao getIncomeDao();

  public abstract ExpenseDao getExpenseDao();

  private static BudgetDatabase INSTANCE;

  public static BudgetDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room
          .databaseBuilder(context.getApplicationContext(), BudgetDatabase.class, "income_room")
          .addCallback(
              new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                  super.onCreate(db);
                  for (Category.Title title : Category.Title.values()) {
                    Category category = new Category();
                    category.setId(title.ordinal());
                    category.setName(title.abbreviation());
                    Executors.newSingleThreadScheduledExecutor().execute(
                        () -> getInstance(context).getCategoryDao().insert(category));

                    //TODO Auto populating categories may not be a good idea. more info needed
                  }
                }
              })
          .build();
    }
    return INSTANCE;
  }
}
