package br.edu.ifnmg.tads.trabalhofinalII;

import br.edu.ifnmg.tads.trabalhofinal.R;
import br.edu.ifnmg.tads.trabalhofinalII.DataAccess.LivroDAO;
import br.edu.ifnmg.tads.trabalhofinalII.DomainModel.Livro;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoEmprestimo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_emprestimo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novo_emprestimo, menu);
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
	
	public void cadastrar(View view){
		
		EditText EdtCodigo = (EditText) this.findViewById(R.id.edtCodigo);
		EditText EdtTitulo = (EditText) this.findViewById(R.id.edtTitulo);
		EditText EdtDataEmprestimo = (EditText) this.findViewById(R.id.edtDataEmprestimo);
		EditText EdtDataDevolucao = (EditText) this.findViewById(R.id.edtDataDevolucao);
		
		String codigo = EdtCodigo.getText().toString().trim();
		String titulo = EdtTitulo.getText().toString().trim();
		String dataemprestimo = EdtDataEmprestimo.getText().toString().trim();
		String datadevolucao = EdtDataDevolucao.getText().toString().trim();
		
		Livro livro = new Livro();
		livro.setCodigo(Integer.parseInt(codigo));
		livro.setTitulo(titulo);
		livro.setDataemprestimo(dataemprestimo);
		livro.setDatadevolucao(datadevolucao);
		
		LivroDAO dao = new LivroDAO(this);
		dao.inserir(livro);
		
		Toast toast = Toast.makeText(this, "Livro cadastrado com sucesso!", Toast.LENGTH_LONG);
		toast.show();
		
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}
	
	public void voltar(View view){
		
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	
	}
	
}
