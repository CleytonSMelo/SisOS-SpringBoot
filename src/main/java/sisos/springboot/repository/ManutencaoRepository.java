package sisos.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Manutencao;
import sisos.springboot.model.Servico;




@Repository
@Transactional
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
  
	@Query("select m from Manutencao m where m.id = ?1")
	Manutencao findManutencaoByid(Long id);
	
	@Query("select count(m) from Manutencao m")
	Long totalmanutencoes();
	
	@Query("select m from Manutencao m where m.deletado = 'FALSE' AND (m.status = 'EM_MANUTENCAO' or m.status = 'AGUARDANDO_MANUTENCAO' or m.status = 'AGUARDANDO_PECA') AND m.tecnico.id = ?1")
	List<Manutencao> findMinhasManutencoesByid(Long id, Pageable pageable);
	
	@Query("select m from Manutencao m where m.deletado = 'FALSE' ")
	List<Manutencao> findManutencoesByid(Pageable pageable);
	
	@Query("select m from Manutencao m where m.deletado = 'FALSE' AND m.status = 'CONCLUIDO'")
	List<Manutencao> findManutencoesByidConcluido(Pageable pageable);
	
	@Query("select m from Manutencao m where m.deletado = 'FALSE' AND (m.status = 'AGUARDANDO_MANUTENCAO' OR m.status = 'EM_MANUTENCAO' or m.status = 'AGUARDANDO_PECA')")
	List<Manutencao> findManutencoesByidAberto(Pageable pageable);
	
	@Query("select m from Manutencao m where m.deletado = 'FALSE' AND m.status = 'AGUARDANDO_RETIRADA'")
	List<Manutencao> findManutencoesByidAguardandoRetirada(Pageable pageable);
		
	@Query("select m from Manutencao m where m.id = (select max(id) from Manutencao e where e.equipamento.id = ?1 AND e.deletado = 'FALSE')")   
	Manutencao buscarUltimoManutencaoCadparaEquipamento(Long id);
	
	@Query(value = "SELECT COUNT(m) AS total, EXTRACT(MONTH FROM m.datafechamento) AS mes," +
            "EXTRACT(YEAR FROM m.datafechamento) AS ano FROM Manutencao m WHERE m.deletado = 'FALSE' AND m.datafechamento >= ?1" +
            " AND m.datafechamento <= ?2 AND m.status = 'CONCLUIDO' GROUP BY ano,mes ORDER BY ano,mes", nativeQuery = true)
    List<Object[]> contarDeAteDataDESC(LocalDate dtDe, LocalDate dtAte);
    
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.deletado = 'FALSE' AND m.setor.id = ?1")
    Long contarPorSetor(Long id);
	
	@Query("SELECT COUNT(m) FROM Manutencao m WHERE (m.status = 'EM_MANUTENCAO' OR m.status ='AGUARDANDO_MANUTENCAO' OR m.status = 'AGUARDANDO_PECA') AND m.deletado = 'FALSE' ")
    Long contarManutencaoAberto();
    
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE (m.status = 'CONCLUIDO') AND m.deletado = 'FALSE' ")
    Long contarManutencaoConcluidos();
    
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE (m.status = 'AGUARDANDO_RETIRADA') AND m.deletado = 'FALSE' ")
    Long contarManutencaoAguardandoRetiradas();
    
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE (m.status = 'EM_MANUTENCAO' OR m.status = 'AGUARDANDO_MANUTENCAO' OR m.status = 'AGUARDANDO_PECA') AND m.deletado = 'FALSE' AND m.tecnico.id = ?1 ")
    Long contarMinhasManutencoes(Long id);
    
    @Query("SELECT COUNT(m) FROM Manutencao m"+
            " WHERE EXTRACT(YEAR FROM m.dataFechamento) = DATE_PART('YEAR', CURRENT_TIMESTAMP) AND m.status = 'CONCLUIDO' AND m.deletado = 'FALSE'")
    Long contarTotalManutencaoAnual();
		
    @Query("SELECT COUNT(m) FROM Manutencao m"+
            " WHERE EXTRACT(YEAR FROM m.dataFechamento)= ?1  AND m.status = 'CONCLUIDO' AND m.deletado = 'FALSE'")
	Long contarTotalManutencoesSelectAnual(Integer data);
    
    
    @Query("select m from Manutencao m "		
			+ "where m.deletado = 'FALSE' "
			+ "AND (m.equipamento.tombo like ?1 OR m.equipamento.numeroSerie like ?2) AND m.status != 'CONCLUIDO'")
    List<Manutencao> verificarManut(String tombo, String numSerie);
    
  
}
