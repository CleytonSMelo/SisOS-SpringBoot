package sisos.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Fotos;


@Repository
@Transactional
public interface FotoRepository extends JpaRepository<Fotos, Long> {
  
	@Query("select f from Fotos f where f.equipamento.id = ?1")
	List<Fotos> findFotosbyIdEquip(Long id);
}
