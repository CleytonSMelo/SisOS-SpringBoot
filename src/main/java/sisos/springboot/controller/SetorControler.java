package sisos.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sisos.springboot.model.Setor;
import sisos.springboot.model.Usuarios;
import sisos.springboot.repository.SetorRepository;
import sisos.springboot.repository.UsuarioRepository;
import sisos.springboot.security.ImplementacaoUserDetailService;

@Controller
public class SetorControler {
	
	@Autowired
	private SetorRepository setorRepository; 

	@RequestMapping(method = RequestMethod.GET , value = "/Home/Setor/Cadastro")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/Home/Setor/Cadastro");		
		modelAndView.addObject("setorobj", new Setor());
		return modelAndView ;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/Home/Setor/SalvarSetor")
    public ModelAndView salvar(Setor setor, RedirectAttributes ra) {
		Setor setor2 = setorRepository.findSetorByid(setor.getId());
			
		if (setor2 == null) {
			setor.setDeletado(false);
			setorRepository.save(setor);
			ra.addFlashAttribute("msg", "Setor Cadastrado com sucesso");
						
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Setor/ListarSetor");
			Iterable<Setor> itusuario = setorRepository.findSetores();
			modelandView.addObject("setores", itusuario);
			modelandView.addObject("setorobj", new Setor());
			return modelandView;	
		}else {
			setor.setDeletado(setor2.getDeletado());
			setorRepository.save(setor);
			ra.addFlashAttribute("msg", "Setor Atualizado com sucesso");
						
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Setor/ListarSetor");
			Iterable<Setor> itusuario = setorRepository.findSetores();
			modelandView.addObject("setores", itusuario);
			modelandView.addObject("setorobj", new Setor());
			return modelandView;
		}					
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Home/Setor/ListarSetor")
	public ModelAndView setores() {
		ModelAndView modelandView = new ModelAndView("/Home/Setor/ListarSetor");
		Iterable<Setor> itusuario = setorRepository.findSetores();
		modelandView.addObject("setores", itusuario);
		modelandView.addObject("setorobj", new Setor());
		return modelandView;
	}
	
	@GetMapping("/Home/Setor/EditarSetor/{idSetor}")
	public ModelAndView Editar(@PathVariable("idSetor") Long idSetor) {
		Optional<Setor> setor = setorRepository.findById(idSetor);		
		ModelAndView modelAndView = new ModelAndView("/Home/Setor/Cadastro");		
		modelAndView.addObject("setorobj", setor.get());
		return modelAndView;
	}
	
	@GetMapping("/Home/Setor/RemoverSetor/{idSetor}")
	public ModelAndView Excluir(@PathVariable("idSetor") Long idSetor, RedirectAttributes ra) {
		Setor setor = setorRepository.findSetorByid(idSetor);
		setor.setDeletado(true);
		setorRepository.save(setor);
		ra.addFlashAttribute("msg", "Setor Removido com sucesso");
		
		ModelAndView modelAndView = new ModelAndView("redirect:/Home/Setor/ListarSetor");		
		modelAndView.addObject("setores", setorRepository.findSetores());
		modelAndView.addObject("setorobj", new Setor());
		return modelAndView;
	}
	    
}
