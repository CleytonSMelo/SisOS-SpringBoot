package sisos.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Setor;
import sisos.springboot.model.Usuarios;


@Repository
@Transactional
public interface SetorRepository extends CrudRepository<Setor, Long> {
  
	
	
	@Query("select s from Setor s where s.id = ?1")
	Setor findSetorByid(Long id);
	
	@Query("select s from Setor s where s.nome like %?1%")
	List<Setor> findSetorByNome(String nome);
	
	@Query("select s from Setor s where s.deletado = 'FALSE' order by s.nome asc")
	List<Setor> findSetores();
	
}
