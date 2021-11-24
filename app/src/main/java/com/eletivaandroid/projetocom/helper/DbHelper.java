package com.eletivaandroid.projetocom.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private final Context context;

    public static int VERSION = 1;
    public static String NAME_DB = "DB_COM";
    public static String TABELA_USUARIOS = "usuarios";
    public static String TABELA_VOOS = "voos";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTableUsuarios = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS +
                " (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " usuario TEXT NOT NULL," +
                " senha TEXT NOT NULL," +
                " fgFinalizada CHAR(1) NOT NULL); ";

        String sqlTableVoos = "CREATE TABLE IF NOT EXISTS " + TABELA_VOOS +
                " (id_voo INTEGER PRIMARY KEY AUTOINCREMENT," +
                " descricao TEXT NOT NULL," +
                " voo_de TEXT NOT NULL," +
                " voo_para TEXT NOT NULL," +
                " data_saida TEXT NOT NULL," +
                " data_volta TEXT NOT NULL," +
                " qtd_passagem_disponivel INTEGER NOT NULL," +
                " status TEXT NOT NULL" +
                ");";

        try {
            db.execSQL(sqlTableUsuarios);
            db.execSQL(sqlTableVoos);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("DB_INFO", "Erro: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
