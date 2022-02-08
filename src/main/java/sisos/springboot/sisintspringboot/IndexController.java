package sisos.springboot.sisintspringboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sisos.springboot.model.Usuarios;
import sisos.springboot.repository.UsuarioRepository;

@Controller
public class IndexController {
	
	

	
	@RequestMapping("/Home/")
	public String index() {
		return "Home/dashboard/index";
	}
	
	@RequestMapping("/Home/dashboard/index")
	public String inicio() {
		
		return "Home/dashboard/index";
		
	}
	
	@RequestMapping("/Home/blank")
	public String branco() {
		
		return "Home/blank";
		
	}
	
	@RequestMapping("/Home/Usuarios/Cadastro")
	public String cadastrouser() {
		
		return "Home/Usuarios/Cadastro";
		
	}
}
