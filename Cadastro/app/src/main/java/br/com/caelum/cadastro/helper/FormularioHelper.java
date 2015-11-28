package br.com.caelum.cadastro.helper;

import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android5388 on 28/11/15.
 */
public class FormularioHelper {

    private Aluno aluno ;

    public FormularioHelper(FormularioActivity activity) {
        aluno = new Aluno();
        aluno.setNome(((EditText) activity.findViewById(R.id.nome)).getText().toString());
        aluno.setTelefone(((EditText) activity.findViewById(R.id.telefone)).getText().toString());
        aluno.setSite(((EditText) activity.findViewById(R.id.site)).getText().toString());
        aluno.setEndereco(((EditText) activity.findViewById(R.id.endereco)).getText().toString());
        aluno.setNota(Double.valueOf(((RatingBar) activity.findViewById(R.id.nota)).getRating()));
    }

    public boolean hasNome() {
        return !this.aluno.getNome().isEmpty();
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void mostrarToastError(FormularioActivity activity) {
        Toast.makeText(activity, "Campo nome não pode ser vazio", Toast.LENGTH_LONG).show();
    }
}
