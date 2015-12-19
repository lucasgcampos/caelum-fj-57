package br.com.caelum.cadastro.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android5388 on 12/12/15.
 */
public class AlunoAdapter extends BaseAdapter {


    private final List<Aluno> alunos;
    private final Activity context;

    public AlunoAdapter(List<Aluno> alunos, Activity context) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = convertView;

        Aluno aluno = alunos.get(position);

        if (linha == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            linha = inflater.inflate(R.layout.item, parent, false);

            if (position % 2 == 0) {
                linha.setBackgroundColor(context.getResources().getColor(R.color.linha_par));
            } else {
                linha.setBackgroundColor(context.getResources().getColor(R.color.linha_impar    ));
            }
        }

        TextView nomeView = (TextView) linha.findViewById(R.id.item_nome);
        TextView telefoneView = (TextView) linha.findViewById(R.id.item_telefone);
        TextView siteView = (TextView) linha.findViewById(R.id.item_site);
        ImageView imagemView = (ImageView) linha.findViewById(R.id.item_foto);

        Bitmap bm;
        if (aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_no_image);
        }

        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);

        nomeView.setText(aluno.getNome());
        if (telefoneView != null) {
            telefoneView.setText(aluno.getTelefone());
        }
        if (siteView != null) {
            siteView.setText(aluno.getSite());
        }
        imagemView.setImageBitmap(bm);

        return linha;
    }
}
