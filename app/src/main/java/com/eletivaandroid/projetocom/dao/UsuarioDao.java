package com.eletivaandroid.projetocom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.eletivaandroid.projetocom.helper.DbHelper;
import com.eletivaandroid.projetocom.models.Usuario;

public class UsuarioDao {
    private Context context;
    private SQLiteDatabase writeIn;
    private SQLiteDatabase readOn;

    public UsuarioDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        writeIn = dbHelper.getWritableDatabase();
        readOn = dbHelper.getReadableDatabase();
    }

    public boolean cadastrar(Usuario usuario) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", usuario.getNome());
            cv.put("cpf", usuario.getCpf());
            cv.put("rg", usuario.getRg());
            cv.put("usuario", usuario.getSenha().toUpperCase());
            cv.put("senha", usuario.getSenha().toUpperCase());
            cv.put("conectado", "N");
            cv.put("ativo", "S");

            writeIn.insert(DbHelper.TABELA_USUARIO, null, cv);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean alterar(Usuario usuario) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", usuario.getNome());
            cv.put("cpf", usuario.getCpf());
            cv.put("rg", usuario.getRg());
            cv.put("usuario", usuario.getSenha());
            cv.put("senha", usuario.getSenha());

            String[] args = { String.valueOf(usuario.getIdUsuario()) };

            writeIn.update(DbHelper.TABELA_USUARIO, cv, "id_usuario = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean logout(int idUsuario){
        try {
            ContentValues cv = new ContentValues();
            cv.put("conectado", "N");

            String[] args = { String.valueOf(idUsuario) };

            writeIn.update(DbHelper.TABELA_USUARIO, cv, "id_usuario = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean alterarStatus(int idUsuario, String ativo){
        try {
            ContentValues cv = new ContentValues();
            cv.put("ativo", ativo);

            String[] args = { String.valueOf(idUsuario) };

            writeIn.update(DbHelper.TABELA_USUARIO, cv, "id_usuario = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean loginUsuario(String usuario, String senha) {
        try {

            String[] args = { usuario.toUpperCase(), senha.toUpperCase(), "N" };

            String sql = "SELECT * FROM " + DbHelper.TABELA_USUARIO + " WHERE usuario = ? AND senha = ? AND conectado = ?";

            int isUsuario = readOn.rawQuery(sql, args).getCount();

            if (isUsuario == 0) return false;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
