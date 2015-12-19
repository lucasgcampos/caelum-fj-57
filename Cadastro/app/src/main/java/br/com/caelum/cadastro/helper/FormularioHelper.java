package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android5388 on 28/11/15.
 */
public class FormularioHelper {

    private Aluno aluno = new Aluno();

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private ImageView foto;
    private Button botaoFoto;

    public FormularioHelper(FormularioActivity activity) {
        aluno = new Aluno();

        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);

        foto = (ImageView) activity.findViewById(R.id.foto);
        botaoFoto = (Button) activity.findViewById(R.id.botaoFoto);
    }

    public Button getFotoButton() {
        return botaoFoto;
    }


    public Aluno getAluno() {

        aluno.setNome((nome).getText().toString());
        aluno.setTelefone((telefone).getText().toString());
        aluno.setSite((site).getText().toString());
        aluno.setEndereco((endereco).getText().toString());
        aluno.setNota(Double.valueOf((nota).getRating()));
        aluno.setCaminhoFoto((String) foto.getTag());

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

        foto.setTag(alunoEdicao.getCaminhoFoto());
        loadImage(alunoEdicao.getCaminhoFoto());
    }

    public Long getId() {
        return aluno != null ? aluno.getId() : null;
    }

    public void loadImage(String localArquivoFoto) {
        if (localArquivoFoto != null) {
            Bitmap fotoUsuario = BitmapFactory.decodeFile(localArquivoFoto);
            Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoUsuario, fotoUsuario.getWidth(), 300, true);
            foto.setImageBitmap(fotoReduzida);
            foto.setTag(localArquivoFoto);
            foto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
