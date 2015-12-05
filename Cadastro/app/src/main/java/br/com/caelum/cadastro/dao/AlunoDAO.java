package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<Aluno> getLista() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        List<Aluno> alunos = new ArrayList<>();

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deletar(Aluno aluno) {
        String[] args = {aluno.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }
}
