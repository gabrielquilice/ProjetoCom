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
    public static final String NAME_DB = "DB_COM";
    public static final String TABELA_USUARIO = "usuario";
    public static final String TABELA_VOO = "voo";
    public static final String TABELA_PASSAGEM = "passaqem";

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTableUsuario = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO +
                " (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " cpf CHAR(14) NOT NULL," +
                " rg CHAR(16) NOT NULL," +
                " usuario TEXT NOT NULL," +
                " senha TEXT NOT NULL," +
                " conectado CHAR(1) NOT NULL," +
                " ativo CHAR(1) NOT NULL); ";

        String sqlTableVoo = "CREATE TABLE IF NOT EXISTS " + TABELA_VOO +
                " (id_voo INTEGER PRIMARY KEY AUTOINCREMENT," +
                " descricao TEXT NOT NULL," +
                " voo_de TEXT NOT NULL," +
                " voo_para TEXT NOT NULL," +
                " data_saida TEXT NOT NULL," +
                " data_volta TEXT NOT NULL," +
                " qtd_passagem_disponivel INTEGER NOT NULL," +
                " qtd_classe_executiva INTEGER NOT NULL," +
                " qtd_classe_economica INTEGER NOT NULL," +
                " status TEXT NOT NULL" +
                ");";

        String sqlTablePassagem = "CREATE TABLE IF NOT EXISTS " + TABELA_PASSAGEM +
                " (id_passagem INTEGER PRIMARY KEY AUTOINCREMENT," +
                " id_voo INTEGER NOT NULL," +
                " id_usuario INTEGER NOT NULL," +
                " preco DOUBLE NOT NULL," +
                " tipo_economica CHAR(1) NOT NULL," +
                " ativo CHAR(1) NOT NULL," +
                " FOREIGN KEY (id_voo) REFERENCES " + TABELA_VOO + "(id_voo)," +
                " FOREIGN KEY (id_usuario) REFERENCES " + TABELA_USUARIO + "(id_usuario));";

        try {
            db.execSQL(sqlTableUsuario);
            db.execSQL(sqlTableVoo);
            db.execSQL(sqlTablePassagem);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("DB_INFO", "Erro: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
