package br.com.caelum.cadastro.helper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android5388 on 12/12/15.
 */
public class JsonStringerAluno {

    public static String toJson(List<Aluno> alunos) {
        JSONStringer json = new JSONStringer();
        try {
            json.object().key("list").array().object().key("aluno").array();

            for (Aluno aluno : alunos) {
                json.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota())
                .endObject();
            }

            json.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            Log.i("ERROR", "Não foi possível converter a lista em JSON");
            throw new RuntimeException("Não foi possível converter a lista em JSON");
        }

        return json.toString();
    }

}
