package sisos.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Equipamento;


@Repository
@Transactional
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
  
	
	@Query("select e from Equipamento e where e.id = ?1")
	Equipamento findEquipamentoByid(Long id);
	
	@Query("select e from Equipamento e where e.deletado = 'FALSE' ")
	List<Equipamento> findListaEquipamentos();
	
}
