package sisos.springboot.enumerador;

public enum StatusManutencao {
   
	VERIFICACAO_PREVIA("Verificação Prévia"),
	AGUARDANDO_MANUTENCAO("Aguardando Manutenção"),
	EM_MANUTENCAO("Em Manutenção"),
	CONCLUIDO("Concluído");
	
	private String nome;
	
	private StatusManutencao(String nome) {
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
		return this.nome;
	}
}
