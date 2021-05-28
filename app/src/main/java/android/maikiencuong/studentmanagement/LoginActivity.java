package android.maikiencuong.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button btnLogin;
    private EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.login_btn_login);
        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_pass);

        email.setText("123456@gmail.com");
        pass.setText("123456");

        btnLogin.setOnClickListener(v -> {
            if (valid()) {
                String strEmail = email.getText().toString().trim();
                String strPass = pass.getText().toString().trim();

                auth.signInWithEmailAndPassword(strEmail, strPass)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, ManagerActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Đăng nhập không thành công. "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private boolean valid() {
        String strEmail = email.getText().toString().trim();
        String strPass = pass.getText().toString().trim();

        if (strEmail.isEmpty()) {
            email.setError("Email không được để trống");
            return false;
        }
        if (strPass.isEmpty()) {
            pass.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    }
}