package android.maikiencuong.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormStudent extends AppCompatActivity {

    private Student student;
    private Button btnSave;
    private EditText name, clazz, diem;
    private String url = "https://60b0906b1f26610017ffe6e8.mockapi.io/student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        getSupportActionBar().hide();

        name = findViewById(R.id.form_name);
        clazz = findViewById(R.id.form_clazz);
        diem = findViewById(R.id.form_diem);
        btnSave = findViewById(R.id.form_btn_save);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            student = (Student) bundle.getSerializable("student");
            name.setText(student.getName());
            clazz.setText(student.getClazz());
            diem.setText(student.getDiem() + "");
        }

        btnSave.setOnClickListener(v -> {
            if (student.getMssv() == 0) {
                addStudent();
            } else {
                updateStudent();
            }
        });
    }

    private void updateStudent() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url + "/" + student.getMssv(),
                response -> {
                    startActivity(new Intent(FormStudent.this, ManagerActivity.class));
                    finish();
                },
                error -> {
                    error.printStackTrace();
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", name.getText().toString().trim());
                map.put("clazz", clazz.getText().toString().trim());
                map.put("diem", diem.getText().toString().trim());
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1));
        RequestQueue queue = Volley.newRequestQueue(FormStudent.this);
        queue.add(stringRequest);
    }

    private void addStudent() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    startActivity(new Intent(FormStudent.this, ManagerActivity.class));
                    finish();
                },
                error -> {
                    error.printStackTrace();
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", name.getText().toString().trim());
                map.put("clazz", clazz.getText().toString().trim());
                map.put("diem", diem.getText().toString().trim());
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1));
        RequestQueue queue = Volley.newRequestQueue(FormStudent.this);
        queue.add(stringRequest);
    }
}