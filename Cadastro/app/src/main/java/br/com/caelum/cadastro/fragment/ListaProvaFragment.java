package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Prova;

/**
 * Created by android5388 on 19/12/15.
 */
public class ListaProvaFragment extends Fragment {

    private ListView listViewProvas;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.fragment_list_provas, container, false);

        listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova matematica = createProva("20/06/2015", "Matemática", Arrays.asList("Álgebra linear", "Cálculo", "Estatística"));
        Prova portugues = createProva("25/07/2015", "Português", Arrays.asList("Complemento nominal", "Oração", "Análise"));

        List<Prova> provas = Arrays.asList(matematica, portugues);

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, provas);
        listViewProvas.setAdapter(adapter);

        listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova provaSelecionada = (Prova) parent.getItemAtPosition(position);

                ProvasActivity activity = (ProvasActivity) getActivity();
                activity.selecionarProva(provaSelecionada);



                Toast.makeText(getActivity(), "Prova selecionada: " + provaSelecionada.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;

    }

    private Prova createProva(String data, String materia, List<String> topicos) {
        Prova prova = new Prova(data, materia);
        prova.setTopicos(topicos);
        return prova;
    }
}
