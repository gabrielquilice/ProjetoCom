package com.eletivaandroid.projetocom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eletivaandroid.projetocom.R;
import com.eletivaandroid.projetocom.dao.UsuarioDao;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsuario;
    private EditText edtSenha;
    private Button btnConfirmar;
    private TextView txtCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.login_edtUser);
        edtSenha = findViewById(R.id.login_edtSenha);
        btnConfirmar = findViewById(R.id.login_btnConfirmar);
        txtCadastro = findViewById(R.id.login_txtCadastro);

        acoesViews();
    }

    private void acoesViews() {
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (edtUsuario.getText().toString().trim().isEmpty() || edtSenha.getText().toString().trim().isEmpty()) return;

                    UsuarioDao usuarioDao = new UsuarioDao(v.getContext());
                    if (!usuarioDao.loginUsuario(edtUsuario.getText().toString().trim(), edtSenha.getText().toString().trim())){
                        Toast.makeText(v.getContext(), "Usuário e/ou senha inválidos", Toast.LENGTH_LONG).show();
                    } else {
                        ;
                    }
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}