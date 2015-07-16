package br.edu.ifnmg.tads.trabalhofinalII;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifnmg.tads.trabalhofinal.R;
import br.edu.ifnmg.tads.trabalhofinalII.DataAccess.LivroDAO;
import br.edu.ifnmg.tads.trabalhofinalII.DomainModel.Livro;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class VisualizaEmprestimo extends Activity {
	
	int idlivro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualiza_emprestimo);
		
		Intent intent = getIntent();
		Bundle params = intent.getExtras();
		idlivro = params.getInt("idlivro");
		Livro livro = new Livro();
		LivroDAO livrodao = new LivroDAO(VisualizaEmprestimo.this);
		livro = livrodao.retornalivro(idlivro);
		
		Log.i("tostring", livro.toString());
		
		TextView tostring = (TextView) findViewById(R.id.lbltostring);
		tostring.setText(livro.toString());
		
		TextView situacao = (TextView) findViewById(R.id.lblsituacao);
		
		String mensagem = "";
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date data = new Date();
		try {
			data = dateformat.parse(livro.getDatadevolucao());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((verificadevolucao(data) < 0) && (livro.getStatus() == 1)){
			mensagem = "O livro não foi devolvido e está dentro do prazo para devolução.";
		} else if ((verificadevolucao(data) == 0) && (livro.getStatus() == 1)){
			mensagem = "O livro não foi devolvido e o prazo para devolução termina hoje";
		} else if ((verificadevolucao(data) > 0) && (livro.getStatus() == 1)){
			mensagem = "O livro não foi devolvido e o prazo para devolução já se encerrou";
		} else if (livro.getStatus() == 0){
			mensagem =  "O livro já foi devolvido";
			Button devolver = (Button) findViewById(R.id.btnDevolverLivro);
			devolver.setText("Voltar");
		}
		
		situacao.setText(mensagem);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualiza_emprestimo, menu);
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
		 		 
		 return data_hoje_format.compareTo(datadevolucao);

	}
	
	public void devolverlivro(View view){
		
		LivroDAO livrodao = new LivroDAO(VisualizaEmprestimo.this);
		Livro livro = livrodao.retornalivro(idlivro);
		
		livro.setStatus(0);
		livrodao.devolverlivro(livro);
		Toast toast = Toast.makeText(this, "Livro devolvido com sucesso!", Toast.LENGTH_LONG);
		toast.show();
		
		
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}
}
