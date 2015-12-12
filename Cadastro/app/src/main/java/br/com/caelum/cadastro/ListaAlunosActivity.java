package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.AlunoAdapter;
import br.com.caelum.cadastro.helper.EnviaAlunosTask;
import br.com.caelum.cadastro.model.Aluno;

import static br.com.caelum.cadastro.helper.JsonStringerAluno.toJson;
import static br.com.caelum.cadastro.helper.WebClient.post;


public class ListaAlunosActivity extends ActionBarActivity {

    private ListView listaAlunos;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.listaAlunos = (ListView) this.findViewById(R.id.lista_aluno);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                edicao.putExtra("aluno", (Aluno) parent.getItemAtPosition(position));
                startActivity(edicao);
            }
        });

        Button botaoAdicionar = (Button) findViewById(R.id.botao);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        this.registerForContextMenu(listaAlunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno alunoSelecionado = (Aluno) listaAlunos.getAdapter().getItem(info.position);

        MenuItem sms = menu.add("Enviar SMS");

        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
        intentSms.putExtra("sms_body", "text default");
        sms.setIntent(intentSms);

        MenuItem mapa = menu.add("Achar no Mapa");
        Intent intentMap = new Intent(Intent.ACTION_VIEW);
        intentMap.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()));
        mapa.setIntent(intentMap);

        MenuItem site = menu.add("Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String url = alunoSelecionado.getSite().startsWith("http://") ? alunoSelecionado.getSite() : "http://" + alunoSelecionado.getSite();
        intentSite.setData(Uri.parse(url));
        site.setIntent(intentSite);

        MenuItem ligar = menu.add("Ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
                startActivity(intent);

                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar o aluno " + alunoSelecionado.getNome() + "?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                dao.deletar(alunoSelecionado);
                                dao.close();
                                carregarLista();
                            }
                        }).setNegativeButton("Não", null).show();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onResume();
        this.carregarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.menu_enviar_notas:
               new EnviaAlunosTask(this).execute();
               return true;
       }

        return super.onOptionsItemSelected(item);
    }

    public void carregarLista() {
        AlunoDAO db = new AlunoDAO(this);
        alunos = db.getLista();
        db.close();

        AlunoAdapter adapter = new AlunoAdapter(alunos, this);

        this.listaAlunos = (ListView) this.findViewById(R.id.lista_aluno);
        listaAlunos.setAdapter(adapter);
    }
}
