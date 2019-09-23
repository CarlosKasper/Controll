package com.example.controll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jow on 29/12/2016.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereFuncionario(String dia, String gastos, String lugar, String descricao){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(CriaBanco.DIA, dia);
        valores.put(CriaBanco.GASTOS, gastos);
        valores.put(CriaBanco.LUGAR, lugar);
        valores.put(CriaBanco.DESCRICAO, descricao);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao cadastrar gasto";
        else
            return "Gasto cadastrado com sucesso";
    }


    public Cursor listaFuncionarios(){
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.DIA, CriaBanco.GASTOS, CriaBanco.LUGAR, CriaBanco.DESCRICAO};
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA, campos, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultaFuncionarioPorID(int id){
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.DIA, CriaBanco.GASTOS, CriaBanco.LUGAR, CriaBanco.DESCRICAO};
        String where = CriaBanco.ID + "=" +id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor consultaFuncionarioPorCPF(String cpf){
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.DIA, CriaBanco.GASTOS, CriaBanco.LUGAR, CriaBanco.DESCRICAO};
        String where = CriaBanco.GASTOS + "=" + cpf;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alterarFuncionario(int id, String dia, String gastos, String lugar, String descricao){
        ContentValues valores;
        String where;


        db = banco.getWritableDatabase();
        where = CriaBanco.ID + "=" +id;

        valores = new ContentValues();
        valores.put(CriaBanco.DIA,dia);
        valores.put(CriaBanco.GASTOS,gastos);
        valores.put(CriaBanco.LUGAR,lugar);
        valores.put(CriaBanco.DESCRICAO,descricao);

        db.update(CriaBanco.TABELA,valores,where,null);
        db.close();
    }

    public void deletarFuncionario(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA,where,null);
        db.close();
    }

}
