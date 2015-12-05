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

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);
    }



    public Aluno getAluno() {
        aluno = new Aluno();
        aluno.setNome((nome).getText().toString());
        aluno.setTelefone((telefone).getText().toString());
        aluno.setSite((site).getText().toString());
        aluno.setEndereco((endereco).getText().toString());
        aluno.setNota(Double.valueOf((nota).getRating()));

        return this.aluno;
    }

    public void mostrarToastError(FormularioActivity activity) {
        Toast.makeText(activity, "Campo nome não pode ser vazio", Toast.LENGTH_LONG).show();
    }

    public void putOnForm(Aluno alunoEdicao) {
        aluno = alunoEdicao;

        nome.setText(alunoEdicao.getNome());
        site.setText(alunoEdicao.getSite());
        endereco.setText(alunoEdicao.getEndereco());
        telefone.setText(alunoEdicao.getTelefone());
        nota.setRating(new Float(alunoEdicao.getNota()));
    }

    public Long getId() {
        return aluno.getId();
    }
}
