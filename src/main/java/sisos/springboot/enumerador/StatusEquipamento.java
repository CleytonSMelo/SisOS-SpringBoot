package sisos.springboot.enumerador;

import java.util.Arrays;

public enum StatusEquipamento {
   
	OK("OK"),
	EM_CONSERTO("Em Conserto"),
	PARA_ALIENACAO("Para Alienacao"),
	ALIENADO("Alienado");
	
	private String nome;
	
	private StatusEquipamento(String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	@Override
	public String toString() {
		return this.name();
	}
	
	public static StatusEquipamento[] getSortedValue(){
		StatusEquipamento[] values = StatusEquipamento.values();
        Arrays.sort(values,(s1,s2)->s1.getNome().toString().compareTo(s2.getNome().toString()));
        return values;
    }
}
