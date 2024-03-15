package com.websarva.wings.android.applicationforvisualizingbudgets;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.websarva.wings.android.applicationforvisualizingbudgets.db.Budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

        //予算名
        public List<String> budgetName;
        //期間
        public List<String> period;
        //合計予算
        public List<Integer> totalBudget;
        //項目
        public List<List<String>> itemListItem;
        //項目別予算
        public List<List<Integer>> itemListBudget;



        //リポジトリ
        private BudgetRepository budgetRepository;
        private int budgetId = -1;

        public MainViewModel(Application application) {
                super(application);
                budgetRepository = new BudgetRepository(application);
        }
        public void prepareBudget() {
                Budget budget = budgetRepository.getBudget(budgetId);
                if(budget != null) {
                        budgetName.add(budget.name);
                }
        }



        //予算名に監視をつける
        private MutableLiveData<List<String>> budgetNameListLiveData = new MutableLiveData<>(new ArrayList<>());


        LiveData<List<String>> getBudgetNameList() {
                //getValue したリストを書き換えられないように、 "Collections.unmodifiableList を返す LiveData" を返す
                return Transformations.switchMap(budgetNameListLiveData, e -> new MutableLiveData<>(Collections.unmodifiableList(e)));
        }
        void addBudgetName(@NonNull String name) {
                budgetName = budgetNameListLiveData.getValue();
                budgetName .add(name);
                budgetNameListLiveData.setValue(budgetName );
        }


}
