package com.adalto.easylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProdutoDAO {

    public void inserir(Context contexto, Produto produto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", produto.getNome() );
        valores.put( "quantidade", produto.getQuantidade() );

        db.insert("produtos" , null , valores );

    }

}
