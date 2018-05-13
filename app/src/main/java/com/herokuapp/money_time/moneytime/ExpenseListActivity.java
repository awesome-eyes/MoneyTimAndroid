package com.herokuapp.money_time.moneytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.herokuapp.money_time.moneytime.retrofit_api.App;
import com.herokuapp.money_time.moneytime.retrofit_api.models.ExpenseCategoryModel;
import com.herokuapp.money_time.moneytime.retrofit_api.models.ExpenseModel;
import com.herokuapp.money_time.moneytime.retrofit_api.JsonList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseListActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView lvExpense;
    ArrayAdapter<String> mAdapter;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        lvExpense = (ListView)findViewById(R.id.lvExpense);
        lvExpense.setOnItemClickListener(this);
        mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1
        );
        lvExpense.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String authHeader = "Token " + App.getAuthToken();
        App.getApi().getExpenseData(authHeader).enqueue(new Callback<JsonList<ExpenseModel>>() {
            @Override
            public void onResponse(Call<JsonList<ExpenseModel>> call,
                                   Response<JsonList<ExpenseModel>> response) {
                try {
                    for (ExpenseModel item : response.body().getResults()) {
                        mAdapter.add(item.toString());
                    }
                    mAdapter.notifyDataSetChanged();

                }
                catch (Exception e){
                    toast = Toast.makeText(
                            getApplicationContext(),
                            "\nERROR\n" + e.toString(),
                            Toast.LENGTH_LONG
                    );
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<JsonList<ExpenseModel>> call, Throwable t) {
                toast = Toast.makeText(
                        getApplicationContext(),
                        "\nFAIL\n" + t.toString(),
                        Toast.LENGTH_LONG
                );
                toast.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getApplicationContext(), AddExpenseActivity.class);
                startActivity(k);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
