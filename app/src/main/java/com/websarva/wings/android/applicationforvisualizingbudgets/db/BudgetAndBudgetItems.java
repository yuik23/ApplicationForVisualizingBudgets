package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BudgetAndBudgetItems {
    @Embedded
    public Budget budget;
    @Relation(parentColumn = "id", entityColumn = "budget_id", entity = BudgetItem.class)
    public List<BudgetItem> budgetItems;
}
