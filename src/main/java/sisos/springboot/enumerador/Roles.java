package sisos.springboot.enumerador;

public enum Roles {
   ROLE_ADMIN("Administrador"),
   ROLE_TEC("Técnico"),
   ROLE_CLI("Cliente");
	
	private String nome;
	
	private Roles(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		
		return this.name();
	}
}
