package com.example.controll;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Listagem extends AppCompatActivity {
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        BancoController crud = new BancoController(getBaseContext());
        cursor = crud.listaFuncionarios();
        String GASTO = this.getIntent().getStringExtra("GASTOS");
        if(GASTO == null) {
            if (cursor.getCount() != 0) {
                String[] nomeCampos = new String[]{CriaBanco.DIA, CriaBanco.GASTOS, CriaBanco.ID};
                int[] idViews = new int[]{R.id.txtDia, R.id.txtGasto, R.id.idDoGasto};
                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_layout, cursor, nomeCampos, idViews, 0);
                ListView lista = (ListView) findViewById(R.id.listView);
                lista.setAdapter(adaptador);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                        Intent intent = new Intent(Listagem.this, Alterar.class);
                        intent.putExtra("codigo", codigo);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "Nenhum gasto encontrado.", Toast.LENGTH_SHORT).show();
            }
        }else{
            cursor = crud.consultaFuncionarioPorCPF(GASTO);
            if (cursor.getCount() != 0) {
                String[] nomeCampos = new String[]{CriaBanco.DIA, CriaBanco.GASTOS, CriaBanco.ID};
                int[] idViews = new int[]{R.id.txtDia, R.id.txtGasto, R.id.idDoGasto};
                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_layout, cursor, nomeCampos, idViews, 0);
                ListView lista = (ListView) findViewById(R.id.listView);
                lista.setAdapter(adaptador);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                        Intent intent = new Intent(Listagem.this, Alterar.class);
                        intent.putExtra("codigo", codigo);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "Nenhum gasto encontrado.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
