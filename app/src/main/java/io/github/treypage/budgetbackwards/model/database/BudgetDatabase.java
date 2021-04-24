//    MIT License
//
//    Copyright (c) 2019 Terrell Page
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.
package io.github.treypage.budgetbackwards.model.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.github.treypage.budgetbackwards.model.dao.CategoryDao;
import io.github.treypage.budgetbackwards.model.dao.ExpenseDao;
import io.github.treypage.budgetbackwards.model.dao.IncomeDao;
import io.github.treypage.budgetbackwards.model.entity.Category;
import io.github.treypage.budgetbackwards.model.entity.Expense;
import io.github.treypage.budgetbackwards.model.entity.Income;

import java.util.concurrent.Executors;

@Database(entities = {Income.class, Category.class,
        Expense.class}, version = 1)
public abstract class BudgetDatabase extends RoomDatabase {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_6_7 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static final Migration MIGRATION_7_8 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    private static BudgetDatabase INSTANCE;

    /**
     * When the BudgetDatabase is initially called and the instance = null the database is populated.
     * The for loop in the method populates the database with every Category title in the Enum inside
     * of the Category entity.
     */
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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                            MIGRATION_6_7, MIGRATION_7_8)
                    .build();
        }
        return INSTANCE;
    }

    public abstract CategoryDao getCategoryDao();

    public abstract IncomeDao getIncomeDao();

    public abstract ExpenseDao getExpenseDao();
}
