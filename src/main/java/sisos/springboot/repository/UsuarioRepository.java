package sisos.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sisos.springboot.model.Usuarios;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuarios, Long> {
  
	@Query("select u from Usuarios u where u.deletado = 'FALSE' AND u.login = ?1")
	Usuarios findUserByLogin(String login);
	
	@Query("select u from Usuarios u where u.deletado = 'FALSE' AND u.nome = ?1")
	Usuarios findUserByNome(String login);
	
	@Query("select u from Usuarios u where u.id = ?1")
	Usuarios findUserByid(Long id);
	
	@Query("select u from Usuarios u where u.login like %?1%")
	List<Usuarios> findUsuariosByLogin(String login);
	
	@Query("select u from Usuarios u where u.deletado = 'FALSE' order by u.id asc")
	List<Usuarios> findUsuarios();
}
