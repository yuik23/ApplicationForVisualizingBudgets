package com.websarva.wings.android.applicationforvisualizingbudgets.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "costs",
        foreignKeys= @ForeignKey(entity=BudgetItem.class,//外部キー設定
        parentColumns = "id",
        childColumns = "budget_item_Id",
        onDelete = ForeignKey.CASCADE)//親を削除すると同時に子も削除するようにCASCADE
)
public class Cost {
    @PrimaryKey(autoGenerate = true)  //オートインクリメントの主キーに
    @NonNull
    public int id;
    @NonNull
    public int cost;
    @NonNull
    public Date date;
    @NonNull
    public int budget_item_id;
}
