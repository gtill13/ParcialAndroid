package com.example.parametros;
/* Parte da "Avaliação parcial 1º"
 * 
 * Com o projeto feito em sala, (se preferir criar um novo)
 * deve ser implementado as seguinte funcionalides: 
 * 1: Quando clicar na lista da Tela1 o Objeto Pessoa da 
 *    linha clicada deve ser enviado para a Tela2 e esse poderá 
 *    ser editado no formulário;
 * 2: Quando clicado na em salvar na Tela2, esse objeto deve ser 
 *    atulizado e a lista deve mostrar o novo valor, isso 
 *    também quando estiver vindo da Tela2 com um novo Objeto Pessoa;
 * 3: Adicionar novos campos o XML da linha da lista;
 * 4: E fazer uso de getters e setters na classe Pessoa.
 * 
 * Apresentaçao dia 17/04/2015 será com os desenvolvedores e será realizadas perguntas.
 */
import java.util.ArrayList;

import meuPacote.Pessoa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Tela1 extends Activity {

	Button btParam, btObjeto;
	ListView minhaLista;
	ArrayList<Pessoa> arrListaPessoas;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela1);
       
        // Arrecadando os objetos da tela
        btParam = (Button)findViewById(R.id.t1_btNovoPorParametro);        
        btObjeto = (Button)findViewById(R.id.t1_btNovoPorObjeto);        
        minhaLista = (ListView) findViewById(R.id.t1_listView_pessoas);
        arrListaPessoas = new ArrayList<Pessoa>();
        
        arrListaPessoas.add(new Pessoa("Marcelo", "12.12.12", "Masculino", "555-5555", "9999-9999", "06331166981"));
        
        minhaLista.setAdapter( new MeuAdapter(getApplicationContext() , arrListaPessoas) );
        minhaLista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicaoClicada, long arg3) {
				Log.d("Fey", "Clicado na linha ("+posicaoClicada+")");
				Intent intencaoNovaTela = new Intent(getApplicationContext(), Tela2.class);
				intencaoNovaTela.putExtra("modoOpercao", "objeto");
				intencaoNovaTela.putExtra("posicaoClicada", posicaoClicada);
				intencaoNovaTela.putExtra("objetoParaEditar", arrListaPessoas.get(posicaoClicada) );
				startActivityForResult(intencaoNovaTela, 123);
			}
		});
        
        // Reescrevendo o EVENTO de clik dos Botões
        // Botton POR PARAMETRO
        btParam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intencaoNovaTela = new Intent(getApplicationContext(), Tela2.class);
				intencaoNovaTela.putExtra("modoOpercao", "parametro");
				startActivityForResult(intencaoNovaTela, 121);
			}
		});
        // Botton POR OBJETO
        btObjeto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intencaoNovaTela = new Intent(getApplicationContext(), Tela2.class);
				intencaoNovaTela.putExtra("modoOpercao", "objeto");
				startActivityForResult(intencaoNovaTela, 122);
			}
		});
        
    }// onCreat
    
    @Override
    protected void onActivityResult( int requestCode // Enviado tela1
    		                       , int resultCode  // Caso a tela2 deu resposta
    		                       , Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if(resultCode==1)
    	{
    		String str;
    		Log.d("Fey", "Ok, a tela2 clicando em SALVAR");
    		Log.d("Fey", " - requestCode = "+requestCode);
    		if(requestCode==121){// Retorno está vindo por Parâmetros
        		Bundle pacote = data.getExtras();
        		str = "\n* * * * \nNome: "+pacote.getString("nome");
        		str+= "\nCPF: "+pacote.getString("cpf");
        		str+= "\nData Nasc.: "+pacote.getString("data");
        		str+= "\nTel. Celular: "+pacote.getString("celu");
        		str+= "\nTel. Fixo: "+pacote.getString("feix");
        		str+= "\nSexo. Sexo: "+pacote.getString("sexo");
        		
        		//textoPrincipal.setText(str+textoPrincipal.getText());
				
    		}
    		else if(requestCode==122)
    		{ // Retorno está vindo por Objeto
    			Log.d("Fey", " - Retorno está vindo por Objeto");
    			arrListaPessoas.add( (Pessoa) data.getSerializableExtra("ObjetoSerializado"));
    			minhaLista.invalidateViews();
    		}
    		else if(requestCode==123)
    		{ 
    			Log.d("Fey", " - Retorno está vindo de uma Edição");
    			
    			int i = data.getExtras().getInt("posicaoClicada");
    			arrListaPessoas.set(i, (Pessoa) data.getSerializableExtra("ObjetoSerializado"));
    			
    			minhaLista.invalidateViews();
    		}
    	}
    	else{
    		Log.d("Fey", " :(  a tela2 não fechou em Salvar!");
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuTela1_1) {
    		Log.d("Fey", " Clicado no menu 1");

            return true;
        }
        else if (id == R.id.menuTela1_2) {
        	Log.d("Fey", " Clicado no menu 2");
            return true;
        }
        else if (id == R.id.menuTela1_3) {
        	Log.d("Fey", " Clicado no menu 3");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}
