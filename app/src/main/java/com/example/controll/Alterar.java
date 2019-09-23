package com.example.controll;

import android.database.Cursor;
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

public class Alterar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Spinner dia;
        final EditText gastos;
        final EditText descricao;
        final Button alterar;
        final Button deletar;
        final Cursor cursor, cursor1;
        final BancoController crud;
        final String codigo;
        final Spinner lugar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoController(getBaseContext());
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
        cursor1 = crud.consultaFuncionarioPorID(Integer.parseInt(codigo));
        switch (cursor1.getString(cursor1.getColumnIndexOrThrow(CriaBanco.DIA))){
            case("Segunda-feira"):
                dia.setSelection(0);
                break;
            case("Terça-feira"):
                dia.setSelection(1);
                break;
            case("Quarta-feira"):
                dia.setSelection(2);
                break;
            case("Quinta-feira"):
                dia.setSelection(3);
                break;
            case("Sexta-feira"):
                dia.setSelection(4);
                break;
            case("Sábado"):
                dia.setSelection(5);
                break;
            case("Domingo"):
                dia.setSelection(6);
                break;
        }


        gastos = (EditText)findViewById(R.id.txtGasto);
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

        descricao = (EditText)findViewById(R.id.txtDescricao);
        alterar = (Button)findViewById(R.id.btnAlterar);
        deletar = (Button)findViewById(R.id.btnDeletar);
        cursor = crud.consultaFuncionarioPorID(Integer.parseInt(codigo));
        switch (cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.LUGAR))){
            case("Padaria"):
                lugar.setSelection(0);
                break;
            case("Bar"):
                lugar.setSelection(1);
                break;
            case("Mercado"):
                lugar.setSelection(2);
                break;
            case("Açougue"):
                lugar.setSelection(3);
                break;
            case("Futebol"):
                lugar.setSelection(4);
                break;
        }

        gastos.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.GASTOS)));
        descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DESCRICAO)));
        alterar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                    if (descricao.getText().toString().length() != 0) {
                        if (gastos.getText().toString().length() <= 11) {
                            crud.alterarFuncionario(Integer.parseInt(codigo), dia.getSelectedItem().toString(), gastos.getText().toString(), lugar.getSelectedItem().toString(), descricao.getText().toString());
                            Toast.makeText(getApplicationContext(), "Gasto alterado com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Digite um valor válido", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite uma descrição válida", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        deletar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                crud.deletarFuncionario(Integer.parseInt(codigo));
                Toast.makeText(getApplicationContext(), "Gasto deletado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
