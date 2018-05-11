package com.herokuapp.money_time.moneytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herokuapp.money_time.moneytime.retrofit_api.App;
import com.herokuapp.money_time.moneytime.retrofit_api.JsonList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGetUsers;
    TextView txUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetUsers = (Button) findViewById(R.id.btnGetUsers);
        btnGetUsers.setOnClickListener(this);

        txUserList = (TextView)findViewById(R.id.tvUserList);
        txUserList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast toast;
        switch(view.getId()) {
            case R.id.btnGetUsers:
                toast = Toast.makeText(getApplicationContext(), "btnGetUsers", Toast.LENGTH_SHORT);
                toast.show();
                App.getApi().getData().enqueue(new Callback<JsonList>() {
                    @Override
                    public void onResponse(Call<JsonList> call, Response<JsonList> response) {
                        txUserList.append("\nGOOD\n");
                        txUserList.append(response.body().getUsers().toString());
                    }
                    @Override
                    public void onFailure(Call<JsonList> call, Throwable t) {
                        txUserList.append("\nFAIL\n");
                        txUserList.append(t.toString());
                    }
                });
                txUserList.append("\nAFTER\n");
                break;
            default:
                toast = Toast.makeText(getApplicationContext(), "Default", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}
