package sisos.springboot.enumerador;



public enum Prioridade {
   
	ALTA(0 , "Pessoa Física"),
	MEDIA(1, "Pessoa Jurídica "),
	BAIXA(2, "Pessoa Jurídica ");
	
	private int cod;
	private String descricao;
	
	private Prioridade(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id Invalido: "+ cod);
	}
}
