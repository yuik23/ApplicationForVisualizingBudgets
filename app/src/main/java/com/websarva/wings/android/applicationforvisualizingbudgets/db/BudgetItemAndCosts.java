package com.websarva.wings.android.applicationforvisualizingbudgets.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BudgetItemAndCosts {
    @Embedded
    public BudgetItem budgetItem;
    @Relation(parentColumn = "id", entityColumn = "budget_item_id", entity = Cost.class)
    public List<Cost> costs;
}
