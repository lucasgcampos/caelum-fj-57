package br.com.caelum.cadastro;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvaFragment;
import br.com.caelum.cadastro.model.Prova;


public class ProvasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isLand()) {
            transaction.replace(R.id.provas_lista, new ListaProvaFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvaFragment());
        }
        transaction.commit();
    }

    public boolean isLand() {
        return getResources().getBoolean(R.bool.isLand);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_provas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selecionarProva(Prova provaSelecionada) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (isLand()) {
            DetalhesProvaFragment detalheFragment = (DetalhesProvaFragment) fragmentManager.findFragmentById(R.id.provas_detalhes);
            detalheFragment.popular(provaSelecionada);
        } else {
            Bundle infos = new Bundle();
            infos.putSerializable("prova", provaSelecionada);

            DetalhesProvaFragment detalhaFragment = new DetalhesProvaFragment();
            detalhaFragment.setArguments(infos);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhaFragment).commit();
        }
    }
}
