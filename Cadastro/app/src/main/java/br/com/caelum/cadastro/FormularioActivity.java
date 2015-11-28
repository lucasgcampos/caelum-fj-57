package br.com.caelum.cadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
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
                this.helper = new FormularioHelper(this);
                if (helper.hasNome()) {
                    AlunoDAO dao = new AlunoDAO(this);
                    dao.inserir(helper.getAluno());
                    dao.close();
                    finish();
                } else {
                    helper.mostrarToastError(this);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
