package com.adalto.easylist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterProduto extends BaseAdapter {

    private List<Produto> listaProdutos;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produto> listaProdutos){
        this.context = context;
        this.listaProdutos = listaProdutos;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return listaProdutos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProdutos.get( i );
    }

    @Override
    public long getItemId(int i) {
        return listaProdutos.get( i ).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if ( view == null){
            view = inflater.inflate(R.layout.layout_lista, null);
            item = new ItemSuporte();
            item.tvId = (TextView) view.findViewById(R.id.tvListaId);
            item.tvNome = (TextView) view.findViewById(R.id.tvListaNome);
            item.tvQuantidade = (TextView) view.findViewById(R.id.tvListaQuantidade);
            item.layout = (LinearLayout) view.findViewById(R.id.layout);
            view.setTag( item );
        }else {
            item = (ItemSuporte) view.getTag();
        }

        Produto produto = listaProdutos.get( i );
        item.tvId.setText( String.valueOf( produto.getId() ) );
        item.tvNome.setText( produto.getNome()  );
        item.tvQuantidade.setText( String.valueOf( produto.getQuantidade() ) );

        if ( produto.getNome().equals( "Lista Vazia!" )){
            item.tvId.setText( " " );
            item.tvQuantidade.setText( " " );
        }

        if ( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.WHITE);
        }else {
            item.layout.setBackgroundColor( Color.rgb(230, 230, 230) );
        }


        return view;
    }


    private class ItemSuporte{
        TextView tvId, tvNome, tvQuantidade;
        LinearLayout layout;
    }

}
