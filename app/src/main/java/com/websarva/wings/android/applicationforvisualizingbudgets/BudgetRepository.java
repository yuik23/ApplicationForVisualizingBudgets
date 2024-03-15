package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.app.Application;

import com.google.common.util.concurrent.ListenableFuture;
import com.websarva.wings.android.applicationforvisualizingbudgets.db.AppDatabase;
import com.websarva.wings.android.applicationforvisualizingbudgets.db.Budget;
import com.websarva.wings.android.applicationforvisualizingbudgets.db.BudgetDAO;

import java.util.concurrent.ExecutionException;

public class BudgetRepository {
    private AppDatabase _db;
    public BudgetRepository(Application application) {  // AppDatabaseのインスタンス生成に必要なapplicationをコンストラクタで取得
        _db = AppDatabase.getDatabase(application);
    }
    public Budget getBudget(int budgetId) {
        BudgetDAO budgetDAO = _db.createBudgetDAO();
        ListenableFuture<Budget> future = budgetDAO.findByPK(budgetId);
        Budget budget = null;
        try {
            budget = future.get();
        }
        catch(ExecutionException ex) {
        }
        catch(InterruptedException ex) {
        }
        return budget;
    }
}

