package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

public interface CostDAO {
    @Query("SELECT * FROM costs WHERE id = :id")
    public ListenableFuture<Budget> findByPK(int id);
    @Insert
    public ListenableFuture<Long> insert(Cost cost);
    @Query("UPDATE costs SET cost=:cost WHERE id = :id")
    public ListenableFuture<Long> updateCost(int cost,int id);
    @Query("DELETE FROM costs WHERE id = :id")
    public ListenableFuture<Integer> delete(int id);
}
