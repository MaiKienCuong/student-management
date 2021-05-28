package android.maikiencuong.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAdd, btnSignout;
    private MyAdapter adapter;
    private String url = "https://60b0906b1f26610017ffe6e8.mockapi.io/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.manager_recycler);
        btnAdd = findViewById(R.id.manager_btn_add);
        btnSignout = findViewById(R.id.manager_btn_signout);

        adapter = new MyAdapter(ManagerActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(ManagerActivity.this, 1));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", new Student());
            Intent intent = new Intent(ManagerActivity.this, FormStudent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }
}