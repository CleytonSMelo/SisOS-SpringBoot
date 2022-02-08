package sisos.springboot.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import sisos.springboot.enumerador.StatusManutencao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cleyton on 19/01/2022.
 */
@Entity
public class Manutencao implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "nomesolicitante")
    private String nomeSolicitante;
	
	@Column(name = "nomeretirante")
    private String nomeRetirante;
	
	@Column(name = "codigomanutencao")
    private String codigoManutencao;
    
	@Column(name = "dataabertura")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;
    
	@Column(name = "datafechamento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFechamento;
    
	@JsonManagedReference("nota-manutencao")
    @OneToMany(mappedBy = "manutencao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Nota> notas;
	
    private boolean deletado;

    @Column(columnDefinition  = "text")
    private String descricao;

    
    @Column(columnDefinition  = "text", name = "descricaofinal")
    private String descricaoFinal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="setor_id")
    private Setor setor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tecnico_id")
    private Usuarios tecnico;

    @Enumerated(EnumType.STRING)
    private StatusManutencao status;  
    
    private Boolean aguardandopeca;

    @OneToOne
    @JoinColumn(name="equipamento_id")
    private Equipamento equipamento;

    @JsonManagedReference
    @OneToMany(mappedBy = "manutencao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LogManutencao> logManutencao = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getCodigoManutencao() {
        return codigoManutencao;
    }

    public void setCodigoManutencao(String codigoManutencao) {
        this.codigoManutencao = codigoManutencao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) { this.setor = setor; }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuarios getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuarios tecnico) {
        this.tecnico = tecnico;
    }

    public StatusManutencao getStatus() {  	
    	return status;  
    }

    public void setStatus(StatusManutencao status) {
    	this.status = status; 
    }
    
    public Equipamento getEquipamento() {  
    	return equipamento;  
    }

	public void setEquipamento(Equipamento equipamento) { 
    	this.equipamento = equipamento; 
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public String getDescricaoFinal() {
        return descricaoFinal;
    }

    public void setDescricaoFinal(String descricaoFinal) {
        this.descricaoFinal = descricaoFinal;
    }

    public List<LogManutencao> getLogManutencao() {
		return logManutencao;
	}

	public void setLogManutencao(List<LogManutencao> logManutencao) {
		this.logManutencao = logManutencao;
	}

	public String getNomeRetirante() {
		return nomeRetirante;
	}

	public void setNomeRetirante(String nomeRetirante) {
		this.nomeRetirante = nomeRetirante;
	}
	
	public List<Nota> getNotas() {
		return notas;
	}

	public int sizeNotas(){ return notas.size(); }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

	
	public Boolean getAguardandopeca() {
		return aguardandopeca;
	}

	public void setAguardandopeca(Boolean aguardandopeca) {
		this.aguardandopeca = aguardandopeca;
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
		Manutencao other = (Manutencao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
    
    
}
