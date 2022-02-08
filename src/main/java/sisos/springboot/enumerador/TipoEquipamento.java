package sisos.springboot.enumerador;

import java.util.Arrays;

public enum TipoEquipamento {
   
	MOUSE("Mouse"),
	TECLADO("Teclado"),
	MONITOR("Monitor"),
	NOBREAK("No-Break"),
	ESTABILIZADOR("Estabilizador"),
	CPU("CPU"),
	NOTEBOOK("Notebook"),
	IMPRESSORA("Impressora"),
	USB("USB"),
	MODEM("Modem"),
	SWITCH("Switch"),
	WEBCAM("Webcam"),
	HD("HD"),
	SSD("SSD"),
	MICROSD("Micro SD"),
	OUTROS("Outros");
	
	private String nome;
	
	private TipoEquipamento(String nome) {
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
	
	public static TipoEquipamento[] getSortedValue(){
		TipoEquipamento[] values = TipoEquipamento.values();
        Arrays.sort(values,(s1,s2)->s1.getNome().toString().compareTo(s2.getNome().toString()));
        return values;
    }
}
