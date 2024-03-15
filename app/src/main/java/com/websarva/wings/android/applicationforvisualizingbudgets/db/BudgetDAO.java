package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface BudgetDAO {
    @Query("SELECT * FROM budgets WHERE id = :id")
    public ListenableFuture<Budget> findByPK(int id);
    @Insert
    public ListenableFuture<Long> insert(Budget budget);
    @Query("UPDATE budgets SET name=:name WHERE id = :id")
    public ListenableFuture<Long> updateName(String name,int id);
    @Query("UPDATE budgets SET period=:period WHERE id = :id")
    public ListenableFuture<Long> updatePeriod(String period,int id);
    @Query("UPDATE budgets SET total_budget=:total_budget WHERE id = :id")
    public ListenableFuture<Long> updateTotalBudget(int total_budget,int id);
    @Query("UPDATE budgets SET remaining_total_budget=remaining_total_budget WHERE id = :id")
    public ListenableFuture<Long> updateRemainingTotalBudget(int remaining_total_budget,int id);
    @Query("DELETE FROM budgets WHERE id = :id")
    public ListenableFuture<Integer> delete(int id);

    @Transaction//BudgetItemsとクエリがわかれているので同一トランザクションで処理できるように
    @Query("SELECT * FROM budgets WHERE id = :id")
    public List<BudgetItem> loadBudgetItems(int id);
}
