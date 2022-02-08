package sisos.springboot.enumerador;

public enum StatusServico {
   
	CANCELADO("Cancelado"),
	EM_ESPERA("Aguardando Execução"),
	EM_EXECUCAO("Em Execução"),
	CONCLUIDO("Concluído");
	
	private String nome;
	
	private StatusServico(String nome) {
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
