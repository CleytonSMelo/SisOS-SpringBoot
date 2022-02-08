package sisos.springboot.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sisos.springboot.enumerador.StatusEquipamento;
import sisos.springboot.enumerador.TipoEquipamento;
import sisos.springboot.model.Manutencao;

/**
 * Created by Cleyton on 19/01/2022.
 */
@Entity
public class Equipamento implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String tombo;
    
    private String numeroSerie;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    
    private TipoEquipamento tipo;
    
    private boolean deletado;

    @Column(columnDefinition  = "text")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Setor setor;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Manutencao> manutencoes = new ArrayList<>();;
    
    @OneToMany(mappedBy = "equipamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fotos> fotos = new ArrayList<>();
    
    
    
    public List<Fotos> getFotos() {
		return fotos;
	}

	public void setFotos(List<Fotos> fotos) {
		this.fotos = fotos;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) { 
    	this.nome = nome; 
    }

    public String getNome() { 
    	return nome; 
    }

    public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public String getDescricao() { 
    	return descricao; 
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Setor getSetor() { return setor; }

    public void setSetor(Setor setor) { 
    	this.setor = setor; 
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public StatusEquipamento getStatus() { 	
    	return status; 
    }

    public void setStatus(StatusEquipamento status) {	
    	this.status = status; 
    }

    public TipoEquipamento getTipo() { 
    	return tipo;
    }

    public void setTipo(TipoEquipamento tipo) { 
    	this.tipo = tipo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
}
