package com.example.parametros;

import java.util.ArrayList;

import meuPacote.Pessoa;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MeuAdapter extends BaseAdapter{
	ArrayList<Pessoa> arrListaPessoas;
	Context           contexto;
	
	public MeuAdapter(Context _contexto, ArrayList<Pessoa> _arrListaPessoas) {
		arrListaPessoas = _arrListaPessoas;
		contexto = _contexto;
	}

	@Override
	public int getCount() {
		return arrListaPessoas.size();
	}

	@Override
	public Object getItem(int position) {
		return arrListaPessoas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return arrListaPessoas.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
		View linhaAdaptada = inflater.inflate( R.layout.linha_para_lista, parent,false );
		// Depois da linha criada, podemos arrecadar suas caracteristicas.
		TextView email    = (TextView) linhaAdaptada.findViewById(R.id.linha_email);
		TextView telC    = (TextView) linhaAdaptada.findViewById(R.id.linha_telc);
		TextView nome     = (TextView) linhaAdaptada.findViewById(R.id.linha_nome);
		ImageView estrela = (ImageView) linhaAdaptada.findViewById(R.id.linha_estrela);
		nome.setText(  arrListaPessoas.get(position).nome );
		telC.setText(  arrListaPessoas.get(position).telC );
		email.setText( arrListaPessoas.get(position).telF );
		if(position%2==0){
			estrela.setImageResource(android.R.drawable.btn_star_big_off);
		}
		else{
			estrela.setImageResource(android.R.drawable.btn_star_big_on);
		}
		return linhaAdaptada; 
	}
}
