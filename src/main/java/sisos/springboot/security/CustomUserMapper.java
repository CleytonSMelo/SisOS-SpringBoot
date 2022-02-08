package sisos.springboot.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import sisos.springboot.model.Usuarios;
import sisos.springboot.repository.UsuarioRepository;

public class CustomUserMapper implements UserDetailsContextMapper {

	
	@Autowired
	private UsuarioRepository usuarioRepository2;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		
        Usuarios usuario = usuarioRepository2.findUserByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado");
		}
		
		Set<GrantedAuthority> perfil = new  HashSet<GrantedAuthority>();
		//perfil.add(new SimpleGrantedAuthority(usuario.getRoles()));
		perfil.add(new SimpleGrantedAuthority(usuario.getRoles().toString()));
		return new User(usuario.getLogin(), usuario.getSenha(), perfil);
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub

	}

}
