package com.herokuapp.money_time.moneytime;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        tableLayout = (TableLayout)findViewById(R.id.tbl_expenses);
        tableLayout.setStretchAllColumns(true);

        TableRow tblHeader = new TableRow(this);
        tblHeader.setBackgroundColor(Color.YELLOW);

//        TextView tvCol = new TextView(this);
        TextView tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26);
        tvCol.setText("Category");
        tblHeader.addView(tvCol);
        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26);
        tvCol.setText("Amount");
        tblHeader.addView(tvCol);
        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26);
        tvCol.setText("Date");
        tblHeader.addView(tvCol);
        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 26);
        tvCol.setText("Location");
        tblHeader.addView(tvCol);
        tableLayout.addView(tblHeader);
//        tableLayout.addView(tblHeader, new TableLayout.LayoutParams(
//                TableRow.LayoutParams.FILL_PARENT,
//                TableRow.LayoutParams.WRAP_CONTENT));

//        lvExpense = (ListView)findViewById(R.id.lvExpense);
//        lvExpense.setOnItemClickListener(this);
//        mAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1
//        );
//        lvExpense.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ExpenseListActivity self = this;
        String authHeader = "Token " + App.getAuthToken();
        App.getApi().getExpenseData(authHeader).enqueue(new Callback<JsonList<ExpenseModel>>() {
            @Override
            public void onResponse(Call<JsonList<ExpenseModel>> call,
                                   Response<JsonList<ExpenseModel>> response) {
                try {
                    for (ExpenseModel item : response.body().getResults()) {
                        TableRow tblRow = new TableRow(self);
                        tblRow.setId(item.getId());
                        tblRow.setLayoutParams(new TableRow.LayoutParams(
//                                TableRow.LayoutParams.
                                TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
//                        mAdapter.add(item.toString());
                        tblRow.setGravity(Gravity.CENTER);
//                        tblRow.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        TextView tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
                        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
//                        tvCol.setTypeface(Typeface.create("amatic-sc", Typeface.BOLD));
                        tvCol.setText(item.getCategory().toString());
                        tblRow.addView(tvCol);
                        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
                        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
//                        tvCol.setTypeface(Typeface.create("amatic_sc_bold", Typeface.BOLD));
                        tvCol.setText(item.getFixedAmount());
                        tblRow.addView(tvCol);
                        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
                        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
//                        tvCol.setTypeface(Typeface.create("amatic-sc-bold", Typeface.BOLD));
                        tvCol.setText(item.getCreated());
                        tblRow.addView(tvCol);
                        tvCol = (TextView)getLayoutInflater().inflate(R.layout.text_view, null);
                        tvCol.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
//                        tvCol.setTypeface(Typeface.create("amatic_sc", Typeface.BOLD));
                        tvCol.setText(item.getLocation().getName().toString());
                        tblRow.addView(tvCol);
                        tableLayout.addView(tblRow);
                    }
//                    mAdapter.notifyDataSetChanged();

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

//    public void addRow(int c0, int c1, int c2, int c3, int c4, int c5) {
//        //Сначала найдем в разметке активити саму таблицу по идентификатору
//        TableLayout tableLayout = (TableLayout) findViewById(R.id.tbl_expense);
//        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
//        LayoutInflater inflater = LayoutInflater.from(this);
//        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
//        TableRow tr = (TableRow)findViewById(R.id.table_row_expense);
//        //Находим ячейку для номера дня по идентификатору
//        TextView tv = (TextView) tr.findViewById(R.id.category);
//        //Обязательно приводим число к строке, иначе оно будет воспринято как идентификатор ресурса
//        tv.setText(Integer.toString(c0));
//        //Ищем следующую ячейку и устанавливаем её значение
//        tv = (TextView) tr.findViewById(R.id.col1);
//        tv.setText(Integer.toString(c1));
//        //...и так далее для всех значений
//        tableLayout.addView(tr); //добавляем созданную строку в таблицу
//    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
