package com.herokuapp.money_time.moneytime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herokuapp.money_time.moneytime.retrofit_api.App;
import com.herokuapp.money_time.moneytime.retrofit_api.JsonList;
import com.herokuapp.money_time.moneytime.retrofit_api.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGetUsers;
    Button btnGetExpenses;
    TextView txUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetUsers = (Button) findViewById(R.id.btnGetUsers);
        btnGetUsers.setOnClickListener(this);


        btnGetExpenses = (Button) findViewById(R.id.btnGetExpenses);
        btnGetExpenses.setOnClickListener(this);

        txUserList = (TextView)findViewById(R.id.tvUserList);
        txUserList.setOnClickListener(this);

        if(App.getAuthToken().equals("")){
            Intent k = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(k);
        }
    }

    @Override
    public void onClick(View view) {
        Toast toast;
        String authHeader = "Token " + App.getAuthToken();
        switch(view.getId()) {
            case R.id.btnGetUsers:
                toast = Toast.makeText(getApplicationContext(), "btnGetUsers", Toast.LENGTH_SHORT);
                toast.show();
                App.getApi().getUserData(authHeader).enqueue(new Callback<JsonList<UserModel>>() {
                    @Override
                    public void onResponse(Call<JsonList<UserModel>> call, Response<JsonList<UserModel>> response) {
                        try {
                            txUserList.append("\nGOOD\n");
                            txUserList.append(response.body().getResults().toString());
                        }
                        catch (Exception e){
                            txUserList.append("\nERROR\n");
                            txUserList.append(e.toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonList<UserModel>> call, Throwable t) {
                        txUserList.append("\nFAIL\n");
                        txUserList.append(t.toString());
                    }
                });
                break;
            case R.id.btnGetExpenses:
                Intent k = new Intent(getApplicationContext(), ExpenseListActivity.class);
                startActivity(k);
                break;
            default:
//                toast = Toast.makeText(getApplicationContext(), "Default", Toast.LENGTH_SHORT);
//                toast.show();
//                App.getApi().getExpenseData(authHeader).enqueue(new Callback<JsonList<ExpenseModel>>() {
//                    @Override
//                    public void onResponse(Call<JsonList<ExpenseModel>> call, Response<JsonList<ExpenseModel>> response) {
//                        txUserList.append("\nGOOD\n");
//                        txUserList.append(response.body().getResults().toString());
//                    }
//                    @Override
//                    public void onFailure(Call<JsonList<ExpenseModel>> call, Throwable t) {
//                        txUserList.append("\nFAIL\n");
//                        txUserList.append(t.toString());
//                    }
//                });
//                txUserList.append("\nAFTER\n");
                break;
        }
    }
}
