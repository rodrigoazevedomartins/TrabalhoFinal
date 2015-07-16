package br.edu.ifnmg.tads.trabalhofinalII.DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDUtil extends SQLiteOpenHelper{

	private static final String NOME_BD = "BD_USUARIO";
	private static final int VERSAO = 1;
	
	private static final String SQL_CREATE_LIVRO =
			"CREATE TABLE LIVRO (idlivro INTEGER PRIMARY KEY, codigo INTEGER, titulo TEXT, dataemprestimo TEXT, datadevolucao TEXT, status INTEGER)";
	
	public BDUtil(Context context) {
		super(context, NOME_BD, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_LIVRO);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
