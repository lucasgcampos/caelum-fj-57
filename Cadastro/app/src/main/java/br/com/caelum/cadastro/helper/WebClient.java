package br.com.caelum.cadastro.helper;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5388 on 12/12/15.
 */
public class WebClient {

    private static final String URL = "http://www.caelum.com.br/mobile";

    public static String post(String json) {
        try {
            HttpPost post = new HttpPost(URL);
            post.setEntity(new StringEntity(json));

            post.setHeader("Accept", "application/json");
            post.setHeader("content-type", "application/json");

            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = null;
            response = client.execute(post);

            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (IOException e) {
            Log.i("ERROR", "Não postou o JSON");
            throw new RuntimeException("Não postou o JSON");
        }
    }

}
