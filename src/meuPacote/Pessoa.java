package meuPacote;

import java.io.Serializable;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	

	public String nome, data, sexo, telF, telC, ncpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelF() {
		return telF;
	}
	public void setTelF(String telF) {
		this.telF = telF;
	}
	public String getTelC() {
		return telC;
	}
	public void setTelC(String telC) {
		this.telC = telC;
	}
	public String getNcpf() {
		return ncpf;
	}
	public void setNcpf(String ncpf) {
		this.ncpf = ncpf;
	}
	public Pessoa(String nome, String data, String sexo, String telF, String telC, String ncpf ){
		this.nome = nome;
		this.data = data;
		this.sexo = sexo;
		this.telF = telF;
		this.telC = telC;
		this.ncpf = ncpf;

	}
	@Override
	public String toString() {
		return "\n******\nNome: "+this.nome+
				"\nData: "+this.data+
				"\nSexo: "+this.sexo +
				"\nFixo: "+this.telF +
				"\nCel: "+this.telC +
				"\nCPF: "+this.ncpf ;
	}

}
