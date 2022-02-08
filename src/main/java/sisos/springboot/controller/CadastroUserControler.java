package sisos.springboot.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sisos.springboot.model.Usuarios;
import sisos.springboot.repository.SetorRepository;
import sisos.springboot.repository.UsuarioRepository;
import sisos.springboot.security.ImplementacaoUserDetailService;

@Controller
public class CadastroUserControler {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SetorRepository setorRepository;
	
	@RequestMapping(method = RequestMethod.GET , value = "/Home/Usuarios/Cadastro")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/Home/Usuarios/Cadastro");		
		modelAndView.addObject("usuarioobj", new Usuarios());
		modelAndView.addObject("setores", setorRepository.findSetores());		
		return modelAndView ;
	}	
	
	@RequestMapping(method = RequestMethod.POST , value = "/Home/Usuarios/SalvarUsuario")
    public ModelAndView salvar(Usuarios usuario, RedirectAttributes ra , @RequestParam("foto") MultipartFile file) throws IOException {
		
		Usuarios user = usuarioRepository.findUserByid(usuario.getId());
		if (user == null) {
//			if (usuario.getRoles().equals("administrador")) {
//				usuario.setRoles("ROLE_ADMIN");
//				
//			}else if (usuario.getRoles().equals("tecnico")) {
//				usuario.setRoles("ROLE_USER");
//			}
			if (file.isEmpty()) {
				
			}else {
				usuario.setImagem(file.getBytes());
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String crypi = encoder.encode(usuario.getSenha());
			usuario.setSenha(crypi);			
			usuario.setDeletado(false);
			usuarioRepository.save(usuario);			
			ra.addFlashAttribute("msg", "Usuário Cadastrado com sucesso");			
			
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Usuarios/ListarUser");
			return modelandView;
		}else {
			
//			if (usuario.getRoles().equals("administrador")) {
//				usuario.setRoles("ROLE_ADMIN");
//				
//			}else if (usuario.getRoles().equals("tecnico")) {
//				usuario.setRoles("ROLE_USER");
//			}
            if (file.isEmpty()) {
				usuario.setImagem(user.getImagem());
			}else {
				usuario.setImagem(file.getBytes());
			}
			
			if(usuario.getSenha().equals(user.getSenha())){
				usuario.setSenha(user.getSenha());
			}else {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String crypi = encoder.encode(usuario.getSenha());
				usuario.setSenha(crypi);
			}
			
			usuario.setDeletado(false);			
			usuarioRepository.save(usuario);
			ra.addFlashAttribute("msg", "Usuário Atualizado com sucesso");
			
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Usuarios/ListarUser");
			return modelandView;
		}		
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Home/Usuarios/ListarUser")
	public ModelAndView usuarios() {
		ModelAndView modelandView = new ModelAndView("/Home/Usuarios/ListarUser");
		Iterable<Usuarios> itusuario = usuarioRepository.findUsuarios();
		modelandView.addObject("usuarios", itusuario);
		modelandView.addObject("usuarioobj", new Usuarios());
		return modelandView;
	}
	
	@GetMapping("**/Home/Usuarios/EditarUsuario/{idUsuario}")
	public ModelAndView Editar(@PathVariable("idUsuario") Long idUsuario) {
		
		Optional<Usuarios> usuario2 = usuarioRepository.findById(idUsuario);
		
		ModelAndView modelAndView = new ModelAndView("/Home/Usuarios/Cadastro");		
		modelAndView.addObject("usuarioobj", usuario2.get());
		modelAndView.addObject("setores", setorRepository.findSetores());
		return modelAndView;
	}
	
	@GetMapping("**/Home/Usuarios/RemoverUsuario/{idUsuario}")
	public ModelAndView Excluir(@PathVariable("idUsuario") Long idUsuario, RedirectAttributes ra) {
		Usuarios usuario = usuarioRepository.findUserByid(idUsuario);
		usuario.setDeletado(true);
		usuarioRepository.save(usuario);
		ra.addFlashAttribute("msg", "Usuário Removido com sucesso");
		
		ModelAndView modelAndView = new ModelAndView("redirect:/Home/Usuarios/ListarUser");		
		modelAndView.addObject("usuarios", usuarioRepository.findUsuarios());
		modelAndView.addObject("usuarioobj", new Usuarios());
		return modelAndView;
	}
	
	@PostMapping("**/Home/PesquisarUsuario")
	public ModelAndView pesquisar(@RequestParam("loginpesquisar") String Loginpesquisar) {
		ModelAndView modelAndView = new ModelAndView("/Home/Usuarios/ListarUser");
		modelAndView.addObject("usuarios", usuarioRepository.findUsuariosByLogin(Loginpesquisar));
		modelAndView.addObject("usuarioobj", new Usuarios());
		return modelAndView;
	}
	
	@ResponseBody
	@GetMapping("**/Home/Usuario/img")
	public byte[] img() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		Usuarios use = usuarioRepository.findUserByNome(authentication.getName());
		if (use.getImagem() == null) {
			return null;
		}else {
			return use.getImagem();
		}	
	}
}
