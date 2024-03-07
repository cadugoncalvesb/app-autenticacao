package com.example.appautenticaoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appautenticaoc.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnCriaConta.setOnClickListener(v -> validaDados());

    }
    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(this, "Informe seu e-mail", Toast.LENGTH_SHORT).show();
        }
        if (senha.isEmpty()){
            Toast.makeText(this, "Informe sua senha", Toast.LENGTH_SHORT).show();
        }
        criarContaFirebase(email, senha);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void criarContaFirebase(String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}