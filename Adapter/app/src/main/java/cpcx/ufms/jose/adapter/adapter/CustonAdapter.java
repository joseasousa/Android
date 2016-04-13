package cpcx.ufms.jose.adapter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cpcx.ufms.jose.adapter.R;
import cpcx.ufms.jose.adapter.model.Lanche;

/**
 * Created by jose on 21/01/2016.
 */
public class CustonAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Lanche> itens;

    public CustonAdapter(List<Lanche> itens,Context context) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Lanche l = itens.get(position);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView =  layoutInflater.inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.nome.setText(l.getNome());

        holder.valor.setText(l.getValor());

        String url = l.getImagem();
        if (url != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(url);
            holder.imagem.setImageBitmap(imagemFoto);
            holder.imagem.setTag(url);
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tvNome) TextView nome;
        @Bind(R.id.tvValor) TextView valor;
        @Bind(R.id.imageView) ImageView imagem;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
