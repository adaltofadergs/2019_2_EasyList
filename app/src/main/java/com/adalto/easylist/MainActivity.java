package com.adalto.easylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvProdutos = findViewById(R.id.lvProdutos);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity( i );
            }
        });

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Produto) adapterView.getItemAtPosition(i)  );
                return true;
            }
        });
    }

    private void excluir(final Produto produto){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + produto.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutoDAO.excluir(MainActivity.this, produto.getId());
                carregarLista();
            }
        });
        alerta.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Produto> lista = ProdutoDAO.getProdutos(this);

        if ( lista.size() == 0 ){
            lvProdutos.setEnabled( false );
            Produto fake = new Produto();
            fake.setQuantidade(0);
            fake.setNome("Lista Vazia!");
            lista.add( fake );
        }else {
            lvProdutos.setEnabled( true );
        }

//        ArrayAdapter<Produto> adapter = new ArrayAdapter(
//                this, android.R.layout.simple_list_item_1,
//                lista);

        AdapterProduto adapter = new AdapterProduto(this, lista);

        lvProdutos.setAdapter( adapter );

    }

}







