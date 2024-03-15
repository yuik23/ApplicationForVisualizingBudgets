package com.websarva.wings.android.applicationforvisualizingbudgets.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_items",
        foreignKeys= @ForeignKey(entity=Budget.class,//外部キー設定
                parentColumns = "id",
                childColumns = "budget_Id",
                onDelete = ForeignKey.CASCADE)//親を削除すると同時に子も削除するようにCASCADE
        )
public class BudgetItem {
    @PrimaryKey(autoGenerate = true)  //オートインクリメントの主キーに
    @NonNull
    public int id;
    @NonNull
    public String item;
    @NonNull
    public int item_budget;
    @NonNull
    public int remaining_item_budget;
    @NonNull
    public int budget_id;
}
