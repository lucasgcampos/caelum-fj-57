package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.model.Aluno;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    private static final int CODE_CAMERA = 1;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioHelper(this);
        final Intent intent = getIntent();

        Aluno alunoEdicao = (Aluno) intent.getSerializableExtra("aluno");
        if (alunoEdicao != null) {
            helper.putOnForm(alunoEdicao);
        }

        Button botaoFoto = helper.getFotoButton();
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + "jpg";

                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
                startActivityForResult(intentFoto, CODE_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                helper.loadImage(this.localArquivoFoto);
            } else {
                this.localArquivoFoto = null;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                Long id = helper.getId();
                Aluno aluno = helper.getAluno();
                aluno.setId(id);

                if (!aluno.hasNome()) {
                    helper.mostrarToastError(this);
                } else {
                    AlunoDAO dao = new AlunoDAO(this);
                    dao.inserirOuAlterar(aluno);
                    dao.close();
                    finish();
                }
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
