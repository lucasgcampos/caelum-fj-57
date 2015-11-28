package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android5388 on 28/11/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "alunos";
    private static final String DATABASE = "agenda";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder("CREATE TABLE ");
        query.append(TABELA);
        query.append(" (id INTEGER PRIMARY KEY, ");
        query.append("nome TEXT NOT NULL, ");
        query.append("telefone TEXT, ");
        query.append("endereco TEXT, ");
        query.append("site TEXT, ");
        query.append("nota REAL);");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST " + TABELA;
        db.execSQL(query);
        onCreate(db);
    }

    public void inserir(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getEndereco());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());

        getWritableDatabase().insert(TABELA, null, values);
    }
}
