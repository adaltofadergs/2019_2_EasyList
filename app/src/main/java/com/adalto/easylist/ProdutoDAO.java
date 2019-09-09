package com.adalto.easylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir(Context contexto, Produto produto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", produto.getNome() );
        valores.put( "quantidade", produto.getQuantidade() );

        db.insert("produtos" , null , valores );

    }
    public static void editar(Context contexto, Produto produto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", produto.getNome() );
        valores.put( "quantidade", produto.getQuantidade() );

        db.update("produtos", valores,
                " id = " + produto.getId(), null);

    }

    public static void excluir(Context contexto, int idProduto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("produtos", " id = " + idProduto,
                null);
    }


    public static List<Produto> getProdutos(Context contexto){
        List<Produto> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome",
                null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Produto p = new Produto();
                p.setId(  cursor.getInt( 0 ) );
                p.setNome( cursor.getString( 1 ) );
                p.setQuantidade( cursor.getDouble( 2 ) );
                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Produto getProdutoById(Context contexto, int idProduto){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM produtos WHERE id = " + idProduto;
        Cursor cursor = db.rawQuery( sql ,null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Produto p = new Produto();
            p.setId(  cursor.getInt( 0 ) );
            p.setNome( cursor.getString( 1 ) );
            p.setQuantidade( cursor.getDouble( 2 ) );

            return p;
        }else {
            return null;
        }
    }

}











