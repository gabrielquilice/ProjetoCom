package com.eletivaandroid.projetocom.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.eletivaandroid.projetocom.helper.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class TarefaDao {
    private Context context;
    private SQLiteDatabase writeIn;
    private SQLiteDatabase readOn;

    public TarefaDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        writeIn = dbHelper.getWritableDatabase();
        readOn = dbHelper.getReadableDatabase();
    }

    public boolean salvar(Tarefa tarefa) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("titulo", tarefa.getTitulo());
            cv.put("fgFinalizada", "N");

            writeIn.insert(DbHelper.TABELA_TAREFAS, null, cv);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public List<Tarefa> getListaTarefas(){
        List<Tarefa> listaTarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ORDER BY id DESC;";
        Cursor cursor = readOn.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId( cursor.getLong(cursor.getColumnIndex("id")) );
            tarefa.setTitulo( cursor.getString( cursor.getColumnIndex("titulo")) );
            tarefa.setFgFinalizada( cursor.getString( cursor.getColumnIndex("fgFinalizada")) );

            listaTarefas.add(tarefa);
        }
        return listaTarefas;
    }

    public boolean alterar(Tarefa tarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("titulo", tarefa.getTitulo());

            String[] args = { tarefa.getId().toString() };

            writeIn.update(DbHelper.TABELA_TAREFAS, cv, "id = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean alterarStatus(Tarefa tarefa){
        try {
            ContentValues cv = new ContentValues();
            cv.put("fgFinalizada", tarefa.getFgFinalizada());

            String[] args = { tarefa.getId().toString() };

            writeIn.update(DbHelper.TABELA_TAREFAS, cv, "id = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    public boolean deletar(Tarefa tarefa) {
        try {
            String[] args = { tarefa.getId().toString() };

            writeIn.delete(DbHelper.TABELA_TAREFAS, "id = ?", args);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
