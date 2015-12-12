package br.com.caelum.cadastro.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.model.Aluno;

import static android.widget.Toast.*;
import static android.widget.Toast.makeText;
import static br.com.caelum.cadastro.helper.JsonStringerAluno.toJson;
import static br.com.caelum.cadastro.helper.WebClient.post;

/**
 * Created by android5388 on 12/12/15.
 */
public class EnviaAlunosTask extends AsyncTask<Void, Object, String> {

    private final Activity context;
    private ProgressDialog progress;

    public EnviaAlunosTask(Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        return post(toJson(new AlunoDAO(context).getLista()));
    }

    @Override
    protected void onPostExecute(String result) {
        makeText(context, result, LENGTH_LONG).show();
        progress.dismiss();
    }
}
