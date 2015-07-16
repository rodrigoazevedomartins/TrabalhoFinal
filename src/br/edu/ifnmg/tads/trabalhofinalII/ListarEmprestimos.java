package br.edu.ifnmg.tads.trabalhofinalII;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifnmg.tads.trabalhofinal.R;
import br.edu.ifnmg.tads.trabalhofinalII.DataAccess.LivroDAO;
import br.edu.ifnmg.tads.trabalhofinalII.DomainModel.Livro;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListarEmprestimos extends Activity {

	ListView lista;
	ArrayAdapter<String> adapter;
	int adapterlayout = android.R.layout.simple_list_item_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_emprestimos);
		
		LivroDAO livrodao = new LivroDAO(ListarEmprestimos.this);
		
		lista = (ListView) findViewById(R.id.listalivros);
		
		List<Livro> livros = livrodao.listarLivros();
		
		ArrayList<String> listalivros = new ArrayList<String>();
		
		for(Livro l : livros){
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date datadevolucao = new Date();
			try {
				datadevolucao = dateformat.parse(l.getDatadevolucao());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String mensagem = "";
			
			if((verificadevolucao(datadevolucao) < 0) && (l.getStatus() == 1)){
				mensagem = "O livro não foi devolvido e está dentro do prazo para devolução.";
			} else if ((verificadevolucao(datadevolucao) == 0) && (l.getStatus() == 1)){
				mensagem = "O livro não foi devolvido e o prazo para devolução termina hoje";
			} else if ((verificadevolucao(datadevolucao) > 0) && (l.getStatus() == 1)){
				mensagem = "O livro não foi devolvido e o prazo para devolução já se encerrou";
			} else if (l.getStatus() == 0){
				mensagem =  "O livro já foi devolvido";
			}
			
			l.setSituacao(mensagem);
				
			listalivros.add(l.toString());
		
		}
		
		adapter = new ArrayAdapter<String>(this, adapterlayout, listalivros);
		
		lista.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_emprestimos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int verificadevolucao(Date datadevolucao){
		 SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		 Date data = new Date();
		 String data_hoje = dateformat.format(data);
		 Date data_hoje_format = new Date();
		 try {
			data_hoje_format = dateformat.parse(data_hoje);
		 } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }	
		 
		 Log.i("data:", data + "");
		 
		 return data_hoje_format.compareTo(datadevolucao);
		 
	}
	
	public void voltar (View view){
		
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	
	}
	
}