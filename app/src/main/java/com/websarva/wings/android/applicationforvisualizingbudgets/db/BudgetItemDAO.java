package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public interface BudgetItemDAO {
    @Query("SELECT * FROM budget_items WHERE budget_id = :budget_id")
    public ListenableFuture<Budget> findByPK(int budget_id);
    @Insert
    public ListenableFuture<Long> insert(BudgetItem budgetItem);
    @Query("UPDATE budget_items SET item=:item WHERE id = :id")
    public ListenableFuture<Long> updateItem(String item,int id);
    @Query("UPDATE budget_items SET item_budget=:item_budget WHERE id = :id")
    public ListenableFuture<Long> updateItemBudget(int item_budget,int id);
    @Query("UPDATE budget_items SET remaining_item_budget=:remaining_item_budget WHERE id = :id")
    public ListenableFuture<Long> updateRemainingItemBudget(int remaining_item_budget,int id);
    @Query("DELETE FROM budget_items WHERE id = :id")
    public ListenableFuture<Integer> delete(int id);

    @Transaction//Costsとクエリがわかれているので同一トランザクションで処理できるように
    @Query("SELECT * FROM budget_items WHERE id = :id")
    public List<Cost> loadCosts(int id);
}
