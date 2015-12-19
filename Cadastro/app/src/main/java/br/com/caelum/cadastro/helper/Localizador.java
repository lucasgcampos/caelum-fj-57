package br.com.caelum.cadastro.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5388 on 19/12/15.
 */
public class Localizador {

    private Geocoder geo;

    public Localizador(Context context) {
        this.geo = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco) {
        try {
            Address resultado = geo.getFromLocationName(endereco, 1).get(0);
            return new LatLng(resultado.getLatitude(), resultado.getLongitude());
        } catch (IOException e) {
            return null;
        }
    }

}
