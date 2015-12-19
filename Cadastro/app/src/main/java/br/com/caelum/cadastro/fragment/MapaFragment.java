package br.com.caelum.cadastro.fragment;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.Localizador;
import br.com.caelum.cadastro.model.Aluno;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

/**
 * Created by android5388 on 19/12/15.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());
        LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");

        Log.i("MAPA", "Coordenadas da Caelum: " + local);

        this.cetralizaNo(local);

        AlunoDAO dao = new AlunoDAO(getActivity());
        List<Aluno> alunos = dao.getLista();

        for (Aluno aluno : alunos) {
            MarkerOptions marcador = new MarkerOptions();
            marcador.title(aluno.getNome());
            marcador.position(localizador.getCoordenada(aluno.getEndereco()));
            getMap().addMarker(marcador);
        }
    }

    private void cetralizaNo(LatLng local) {
        GoogleMap mapa = getMap();

        mapa.moveCamera(newLatLngZoom(local, 11));

    }
}
