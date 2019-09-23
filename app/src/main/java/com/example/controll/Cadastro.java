package com.example.controll;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity {
    Gasto gasto = new Gasto();
    Spinner lugar;
    Spinner dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button cadastrar = (Button)findViewById(R.id.btnCadastra);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastrar();
            }
        });

        Button voltar = (Button)findViewById(R.id.btnVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastro.super.finish();
            }
        });


        lugar = (Spinner)findViewById(R.id.spinnerLugar);

        List<String> lista = new ArrayList<String>();
        lista.add("Padaria");
        lista.add("Bar");
        lista.add("Mercado");
        lista.add("Açougue");
        lista.add("Futebol");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lugar.setAdapter(dataAdapter);


        dia = (Spinner)findViewById(R.id.spinnerdia);

        List<String> lista1 = new ArrayList<String>();
        lista1.add("Segunda-feira");
        lista1.add("Terça-feira");
        lista1.add("Quarta-feira");
        lista1.add("Quinta-feira");
        lista1.add("Sexta-feira");
        lista1.add("Sábado");
        lista1.add("Domingo");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dia.setAdapter(dataAdapter1);
            }

    public void Cadastrar(){
        String resultado;
        BancoController crud = new BancoController(getBaseContext());
        EditText campoGasto, campoDescricao;
        campoGasto = (EditText) findViewById(R.id.txtGasto);
        campoDescricao = (EditText)findViewById(R.id.txtDescricao);


            if (campoGasto.getText().toString().length() != 0) {
                if(campoDescricao.getText().toString().length() != 0) {
                    gasto.setDia(String.valueOf(dia.getSelectedItem().toString()));
                    gasto.setGasto(campoGasto.getText().toString());
                    gasto.setLugar(String.valueOf(lugar.getSelectedItem().toString()));
                    gasto.setDescricao(campoDescricao.getText().toString());

                    dia.setSelection(0);
                    campoGasto.setText("");
                    lugar.setSelection(0);
                    campoDescricao.setText("");
                    resultado = crud.insereFuncionario(gasto.getDia(), gasto.getGasto(), gasto.getLugar(), gasto.getDescricao());

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Digite um descrição válido", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Digite um valor válido!", Toast.LENGTH_SHORT).show();
            }



    }




}
