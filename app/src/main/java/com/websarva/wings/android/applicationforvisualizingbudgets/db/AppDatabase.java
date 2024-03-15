package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Budget.class,BudgetItem.class, Cost.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase _instance;
    //シングルトンパターンでインスタンスを生成
    public static AppDatabase getDatabase(Context context) {
        if (_instance == null) {
            _instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "budgets_db").build();  // (3)
        }
        return _instance;
    }
    public abstract BudgetDAO createBudgetDAO();
    public abstract BudgetItemDAO createBudgetItemDAO();
    public abstract CostDAO createCostDAO();

}
