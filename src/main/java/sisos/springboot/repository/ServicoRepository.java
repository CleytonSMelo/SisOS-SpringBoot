package sisos.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Servico;


@Repository
@Transactional
public interface ServicoRepository extends CrudRepository<Servico, Long> {
  
	
}
