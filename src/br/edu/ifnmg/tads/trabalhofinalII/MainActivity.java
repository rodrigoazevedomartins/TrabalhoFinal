package br.edu.ifnmg.tads.trabalhofinalII;

import java.util.List;

import br.edu.ifnmg.tads.trabalhofinal.R;
import br.edu.ifnmg.tads.trabalhofinalII.DataAccess.LivroDAO;
import br.edu.ifnmg.tads.trabalhofinalII.DomainModel.Livro;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void cadastraremprestimos(View view){
		
		Intent intent = new Intent();
		intent.setClass(this, NovoEmprestimo.class);
		startActivity(intent);
		finish();
		
	}
	
	public void listaremprestimos(View view){
		
		Intent intent = new Intent();
		intent.setClass(this, ListarEmprestimos.class);
		startActivity(intent);
		finish();
		
	}
	
	@SuppressLint("NewApi")
	public void gerarnotificacao(View view){
		
		int notificacao = 0; 
		
		LivroDAO livrodao = new LivroDAO(MainActivity.this);
		
		List<Livro> livros = livrodao.listarLivros();
		
		for (Livro l : livros){
			
			if (l.getStatus() == 1){
				
				NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				Intent intent = new Intent();
				
				Bundle params = new Bundle();
				
				params.putInt("idlivro", l.getId());
				
				Log.i("idlivro", l.getId()+"");
				
				intent.putExtras(params);
				
				intent.setClass(this, VisualizaEmprestimo.class);
					
				PendingIntent p = PendingIntent.getActivity(this, l.getId(), intent, 0);
				
				NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
				
				builder.setTicker("Existe livro para devolução");
				builder.setContentTitle("Titulo do Livro: " + l.getTitulo());
				builder.setContentText("Data p/ devolução do livro: " + l.getDatadevolucao());
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
				builder.setContentIntent(p);
				
				Notification n = builder.build();
				n.vibrate = new long[]{150, 300, 150, 600};
				n.flags = Notification.FLAG_AUTO_CANCEL;
				nm.notify(l.getId(), n);
				
				notificacao = 1;
				
				try{
					Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
					Ringtone toque = RingtoneManager.getRingtone(this, som);
					toque.play();
				}
				catch(Exception e){}
			}
		}
		
		if (notificacao == 0){
			Toast toast = Toast.makeText(this, "Não existe livro para devolver!", Toast.LENGTH_LONG);
			toast.show();
		}
	}	
}
