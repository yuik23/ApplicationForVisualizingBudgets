package com.websarva.wings.android.applicationforvisualizingbudgets.db;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "budgets")
public class Budget {
    @PrimaryKey(autoGenerate = true)  //オートインクリメントの主キーに
    @NonNull
    public int id;
    @NonNull
    public String name;
    @NonNull
    public String period;
    @NonNull
    public int total_budget;
    @NonNull
    public int remaining_total_budget;
}

