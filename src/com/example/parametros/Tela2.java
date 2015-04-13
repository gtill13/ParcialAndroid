package com.example.parametros;

import meuPacote.Pessoa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Tela2 extends Activity {

	Button btSalvar, btVoltar;
	EditText edNome, edDatNas, edCpf, edTelFix, edTelCel;
	RadioGroup rgSexo;
	RadioButton rbMas, rbFem;
	
	String modoDeOperacao;
	int posicaoClicada;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela2);
		
        // Arrecadando os objetos da tela
		btSalvar = (Button) findViewById(R.id.t2_salvar);
		btVoltar = (Button) findViewById(R.id.t2_bt_voltar);
		edNome   = (EditText) findViewById(R.id.t2_ed_nome);
		edDatNas   = (EditText) findViewById(R.id.t2_ed_datNasc);
		edCpf      = (EditText) findViewById(R.id.t2_editText_CPF);
		edTelFix   = (EditText) findViewById(R.id.t2_ditText_telFix);
		edTelCel   = (EditText) findViewById(R.id.t2_editText_telCel);
		rgSexo     = (RadioGroup) findViewById(R.id.t2_radGrup_sexo);
		rbMas     = (RadioButton) findViewById(R.id.t2_radioButton_masc);
		rbFem     = (RadioButton) findViewById(R.id.t2_radioButton_femi);

		posicaoClicada = 0;
		
		
		
		// Valores enviados de tela1 para tela2
		Bundle pacote = getIntent().getExtras();
		modoDeOperacao = pacote.getString("modoOpercao","");
		Log.d("Fey", "tela2 modoDeOperacao = ("+modoDeOperacao+")");
		
		Pessoa pessoaRecebida = (Pessoa) pacote.getSerializable("objetoParaEditar");
		
		if(pessoaRecebida!= null){
			setTitle("Editar Pessoa ("+pessoaRecebida.nome+")");
			
			posicaoClicada = pacote.getInt("posicaoClicada");
			
			Log.d("Fey", pessoaRecebida.getNome()+pessoaRecebida.getData()+pessoaRecebida.getNcpf()+pessoaRecebida.getTelF()+pessoaRecebida.getTelC()+pessoaRecebida.getSexo());
			
			edNome  .setText(pessoaRecebida.getNome());
			edDatNas.setText(pessoaRecebida.getData());
			edCpf   .setText(pessoaRecebida.getNcpf());
			edTelFix.setText(pessoaRecebida.getTelF());
			edTelCel.setText(pessoaRecebida.getTelC());
			
			if (pessoaRecebida.getSexo().equalsIgnoreCase("Masculino"))
			{	
				rbMas.setChecked(true);
				rbFem.setChecked(false);
			}
			else
			{
				rbFem.setChecked(true);
				rbMas.setChecked(false);
			}	
		}
		
		// Reescrevendo o EVENTO de clik dos Botões
        // Botton VOLTAR
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		 // Botton SALVAR
		btSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Fey","Clidado em Salvar, modoDeOperacao = "+modoDeOperacao);
				Intent intencaoDeFechar = new Intent(); // Esse vai ser o Data
				setResult(1, intencaoDeFechar);// SET RESULT, a tela1 vai ganhar em resultCode
				
				RadioButton radioSelecionado = (RadioButton) findViewById( rgSexo.getCheckedRadioButtonId() );

				if(modoDeOperacao.equals("parametro"))
				{
					Log.d("Fey"," - parametro");
					intencaoDeFechar.putExtra("nome", edNome.getText().toString() );
					intencaoDeFechar.putExtra("cpf" , edCpf.getText().toString() );
					intencaoDeFechar.putExtra("data", edDatNas.getText().toString() );
					intencaoDeFechar.putExtra("celu", edTelCel.getText().toString() );
					intencaoDeFechar.putExtra("feix", edTelFix.getText().toString() );
					intencaoDeFechar.putExtra("sexo", radioSelecionado.getId() == R.id.t2_radioButton_masc 
														? "Masculino" 
														: "Feminino" 
											 );
					
				}
				else
				{
					Log.d("Fey"," - Objeto");
					
					intencaoDeFechar.putExtra("posicaoClicada", posicaoClicada);
					
					intencaoDeFechar.putExtra("ObjetoSerializado"
							, new Pessoa( edNome.getText().toString()
									    , edDatNas.getText().toString() 
									    , radioSelecionado.getId() == R.id.t2_radioButton_masc ? "Masculino": "Feminino"
									    , edTelFix.getText().toString()
									    , edTelCel.getText().toString()
									    , edCpf.getText().toString()

										)
							);
				}
				finish();
			}
		});
		
	}
}
