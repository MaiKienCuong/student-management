package android.maikiencuong.studentmanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Student> students;
    private Context context;
    private String url = "https://60b0906b1f26610017ffe6e8.mockapi.io/student";

    public MyAdapter(Context context) {
        this.context = context;
        update();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);

        holder.name.setText("Tên: " + student.getName());
        holder.clazz.setText("Lớp học: " + student.getClazz());
        holder.diem.setText("Điểm TB: " + student.getDiem());

        holder.btnDelete.setOnClickListener(v -> {
            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url + "/" + student.getMssv(),
                    response -> {
                        Toast.makeText(context, "Xóa thành công student", Toast.LENGTH_SHORT).show();
                        update();
                    },
                    error -> {
                        error.printStackTrace();
                    });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1));
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(stringRequest);
        });

        holder.btnEdit.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            Intent intent = new Intent(context, FormStudent.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void update() {
        students = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
            try {
                for (int i = response.length() - 1; i >= 0; i--) {
                    JSONObject object = (JSONObject) response.get(i);

                    int id = object.getInt("id");
                    double diem = object.getDouble("diem");
                    String name = object.getString("name");
                    String clazz = object.getString("clazz");

                    students.add(new Student(id, name, clazz, diem));
                }
                notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1));
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);
    }

}
