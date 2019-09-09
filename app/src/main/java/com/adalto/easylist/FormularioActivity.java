package com.adalto.easylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etQuantidade;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNome);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String qtd = etQuantidade.getText().toString();

        if( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
        }
    }



}





,