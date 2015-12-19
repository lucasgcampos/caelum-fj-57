package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Prova;

/**
 * Created by android5388 on 19/12/15.
 */
public class DetalhesProvaFragment extends Fragment {

    private Prova prova;
    private TextView data;
    private TextView materia;
    private ListView topicos;

    public void buscaComponentes(View layout) {
        this.data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        this.materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        this.topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void popular(Prova prova) {
        if (prova != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());


            topicos.setAdapter(adapter);
            data.setText(prova.getData());
            materia.setText(prova.getMateria());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        if (getArguments() != null) {
            this.prova = (Prova) getArguments().getSerializable("prova");
        }

        buscaComponentes(layout);
        popular(prova);

        return layout;
    }
}
