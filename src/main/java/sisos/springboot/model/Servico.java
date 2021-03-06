package sisos.springboot.model;

import javax.persistence.*;

import sisos.springboot.enumerador.Prioridade;
import sisos.springboot.enumerador.StatusServico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created by samue on 09/09/2017.
 */
@Entity
public class Servico implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String titulo;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private String telefoneRetorno;

    @Column(columnDefinition = "text")
    private String descricao;

    private boolean deletado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuarios tecnico;

    @Enumerated(EnumType.STRING)
    private StatusServico statusServico;
    
    //private String statusServico;
    
    private String codigoServico;

    

    @ManyToOne(fetch = FetchType.EAGER)
    private Setor setor;

    @Column(name="nome_solicitante")
    private String nomeSolicitante;

   // private Prioridade prioridade;
    
    private int prioridade;
    
    @Transient
    private boolean assumirServico;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuarios getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuarios tecnico) {
        this.tecnico = tecnico;
    }

    public StatusServico getStatusServico() { return statusServico; }

    public void setStatusServico(StatusServico statusServico) { this.statusServico = statusServico;}

   /* public String getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}*/
    
    public String getCodigoServico() {
        return codigoServico;
    }

	public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }  

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

   // public Prioridade getPrioridade() {  return prioridade; }

    //public void setPrioridade(Prioridade prioridade) {  this.prioridade = prioridade;}

    public Prioridade getTipo() {
		return Prioridade.toEnum(prioridade);
	}

	public void setTipo(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}   

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public boolean isAssumirServico() {
        return assumirServico;
    }

    public void setAssumirServico(boolean assumirServico) {
        this.assumirServico = assumirServico;
    }

    public String getTelefoneRetorno() {
        return telefoneRetorno;
    }

    public void setTelefoneRetorno(String telefoneRetorno) {
        this.telefoneRetorno = telefoneRetorno;
    }

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servico servico = (Servico) o;

        if (deletado != servico.deletado) return false;
        if (id != null ? !id.equals(servico.id) : servico.id != null) return false;
        if (titulo != null ? !titulo.equals(servico.titulo) : servico.titulo != null) return false;
        if (dataAbertura != null ? !dataAbertura.equals(servico.dataAbertura) : servico.dataAbertura != null)
            return false;
        if (dataFechamento != null ? !dataFechamento.equals(servico.dataFechamento) : servico.dataFechamento != null)
            return false;
        if (descricao != null ? !descricao.equals(servico.descricao) : servico.descricao != null) return false;
        if (tecnico != null ? !tecnico.equals(servico.tecnico) : servico.tecnico != null) return false;
        if (statusServico != servico.statusServico) return false;
        if (codigoServico != null ? !codigoServico.equals(servico.codigoServico) : servico.codigoServico != null)
            return false;
        if (tarefas != null ? !tarefas.equals(servico.tarefas) : servico.tarefas != null) return false;
        if (setor != null ? !setor.equals(servico.setor) : servico.setor != null) return false;
        return prioridade == servico.prioridade;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (dataAbertura != null ? dataAbertura.hashCode() : 0);
        result = 31 * result + (dataFechamento != null ? dataFechamento.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (deletado ? 1 : 0);
        result = 31 * result + (tecnico != null ? tecnico.hashCode() : 0);
        result = 31 * result + (statusServico != null ? statusServico.hashCode() : 0);
        result = 31 * result + (codigoServico != null ? codigoServico.hashCode() : 0);
        result = 31 * result + (tarefas != null ? tarefas.hashCode() : 0);
        result = 31 * result + (setor != null ? setor.hashCode() : 0);
        result = 31 * result + (prioridade != null ? prioridade.hashCode() : 0);
        return result;
    }*/
}
