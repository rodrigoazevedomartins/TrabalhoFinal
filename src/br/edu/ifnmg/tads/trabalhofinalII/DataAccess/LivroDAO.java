package br.edu.ifnmg.tads.trabalhofinalII.DataAccess;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifnmg.tads.trabalhofinalII.DomainModel.Livro;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LivroDAO {

	private Context context;
	
	public LivroDAO(Context context){
		this.context = context;
	}
	
	public void inserir(Livro livro){
		BDUtil bdUtil = new BDUtil(this.context);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put("codigo", livro.getCodigo());
		contentValues.put("titulo", livro.getTitulo());
		contentValues.put("dataemprestimo", livro.getDataemprestimo());
		contentValues.put("datadevolucao", livro.getDatadevolucao());
		contentValues.put("status", livro.getStatus());
		
		bdUtil.getWritableDatabase().insert("LIVRO", null, contentValues);
		
	}
	
	public List<Livro> listarLivros(){
		BDUtil bdUtil = new BDUtil(this.context);
		
		String[] colunas = {"idlivro", "codigo", "titulo", "dataemprestimo", "datadevolucao", "status"};
		
		SQLiteDatabase db =  bdUtil.getReadableDatabase();
		
		Cursor c = db.query(
				"LIVRO", // The table to query
				colunas, // The columns to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				"datadevolucao desc" // The sort order
		);
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		while(c.moveToNext()){
			Livro livro = new Livro();
			livro.setId(c.getInt(0)); 
			livro.setCodigo(c.getInt(1));
			livro.setTitulo(c.getString(2));
			livro.setDataemprestimo(c.getString(3));
			livro.setDatadevolucao(c.getString(4));
			livro.setStatus(c.getInt(5));
			livros.add(livro);
		}
		
		return livros;
	}
	
	public Livro retornalivro(int idlivro){
		
		Livro livro = new Livro();
		
		BDUtil bdutil = new BDUtil(this.context);
				
		String sqlquery = "select * from LIVRO where idlivro = ?";
		
		String[] args = {idlivro + ""};
		
		SQLiteDatabase db =  bdutil.getReadableDatabase();
		
		Cursor c = db.rawQuery(sqlquery, args);
		
		c.moveToFirst();
		
		livro.setId(c.getInt(0));
		livro.setCodigo(c.getInt(1));
		livro.setTitulo(c.getString(2));
		livro.setDataemprestimo(c.getString(3));
		livro.setDatadevolucao(c.getString(4));
		livro.setStatus(c.getInt(5));
		
		return livro;
	}
	
	public void devolverlivro(Livro livro){
		
		BDUtil bdutil = new BDUtil(this.context);
		
		String sqlquery = "update LIVRO set status = ? where idlivro = ?";
		
		String[] args = {livro.getStatus()+"", livro.getId()+""};
		
		bdutil.getWritableDatabase().execSQL(sqlquery, args);
		
	}
	
}