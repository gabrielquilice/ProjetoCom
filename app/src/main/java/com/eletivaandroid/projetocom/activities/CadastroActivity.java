package com.eletivaandroid.projetocom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eletivaandroid.projetocom.R;
import com.eletivaandroid.projetocom.dao.UsuarioDao;
import com.eletivaandroid.projetocom.models.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtCPF;
    private EditText edtRG;
    private EditText edtUsuario;
    private EditText edtSenha;
    private Button btnCadastrar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.cadUsuario_edtNome);
        edtCPF = findViewById(R.id.cadUsuario_edtCPF);
        edtRG = findViewById(R.id.cadUsuario_edtRG);
        edtUsuario = findViewById(R.id.cadUsuario_edtUsuario);
        edtSenha = findViewById(R.id.cadUsuario_edtSenha);
        btnCadastrar = findViewById(R.id.cadUsuario_btnCadastrar);
        btnCancelar = findViewById(R.id.cadUsuario_btnCancelar);

        acoesViews();
    }

    private void acoesViews() {

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = new Usuario();
                    UsuarioDao usuarioDao = new UsuarioDao(v.getContext());

                    if (edtNome.getText().toString().isEmpty() || edtCPF.getText().toString().isEmpty() ||
                            edtRG.getText().toString().isEmpty() || edtUsuario.getText().toString().isEmpty() ||
                                edtSenha.getText().toString().isEmpty()) return;

                    usuario.setNome(edtNome.getText().toString());
                    usuario.setCpf(edtCPF.getText().toString());
                    usuario.setRg(edtRG.getText().toString());
                    usuario.setUsuario(edtUsuario.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());

                    if (usuarioDao.cadastrar(usuario)) {
                        Toast.makeText(v.getContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}