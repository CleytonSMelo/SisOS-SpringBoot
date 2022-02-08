package sisos.springboot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sisos.springboot.model.Equipamento;
import sisos.springboot.model.Fotos;
import sisos.springboot.repository.EquipamentoRepository;
import sisos.springboot.repository.FotoRepository;
import sisos.springboot.repository.SetorRepository;

@Controller
public class EquipamentoControler {

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@RequestMapping(method = RequestMethod.GET , value = "**/Home/Equipamento/Cadastro")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/Home/Equipamento/Cadastro");		
		modelAndView.addObject("equipamentoobj", new Equipamento());
		modelAndView.addObject("setores", setorRepository.findSetores());
		return modelAndView ;
	}	
		
	private static String UPLOADED_FOLDER = "C:/temp/";
	@PostMapping("**/Home/Equipamento/Salvar")
    public ModelAndView salvar(Equipamento equipamento, RedirectAttributes ra , @RequestParam("foto") MultipartFile file) throws IOException {
		
		if (equipamento.getId() == null) {			
			equipamento.setDataCadastro(LocalDate.now());			
			equipamento.setDeletado(false);
			Equipamento equip = equipamentoRepository.save(equipamento);
			
	        if (file.isEmpty()) {
				
			}else {				
				byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER +""+equip.getId()+""+file.getOriginalFilename());	                      
	            Files.write(path, bytes);				
				Fotos fotos = new Fotos();
				fotos.setEquipamento(equip);
				fotos.setNomeFoto(""+equip.getId()+""+file.getOriginalFilename());
				fotoRepository.save(fotos);
			}
			ra.addFlashAttribute("msg", "Equipamento Cadastrado com sucesso");						
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Equipamento/Cadastro");
			return modelandView;	
		}else {
			Equipamento equip= equipamentoRepository.findEquipamentoByid(equipamento.getId());
			equipamento.setDataCadastro(equip.getDataCadastro());	
			equipamento.setDeletado(false);			
			Equipamento equip2 = equipamentoRepository.save(equipamento);
			
            if (file.isEmpty()) {
				
			}else {				
				byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER +""+equip2.getId()+""+file.getOriginalFilename());          
	            Files.write(path, bytes);				
				Fotos fotos = new Fotos();
				fotos.setEquipamento(equip2);
				fotos.setNomeFoto(""+equip2.getId()+""+file.getOriginalFilename());
				fotoRepository.save(fotos);
			}			
			ra.addFlashAttribute("msg", "Equipamento Atualizado com sucesso");						
			ModelAndView modelandView = new ModelAndView("redirect:/Home/Equipamento/ListarEquipamento");
			return modelandView;
		}			
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "**/Home/Equipamento/ListarEquipamento")
	public ModelAndView equipamentos(ModelAndView model) {
		ModelAndView modelandView = new ModelAndView("/Home/Equipamento/ListarEquipamento");
		modelandView.addObject("equipamentos", equipamentoRepository.findListaEquipamentos());
		modelandView.addObject("equipamentoobj", new Equipamento());
		return modelandView;
	}
	
	@RequestMapping(value = "**/Home/Equipamento/FotoEquipamento/{idEquipamento}", method = RequestMethod.GET)
	public ModelAndView FotoEquipamento(@PathVariable("idEquipamento") Long idEquipamento) {		
	    List<Fotos> foto = fotoRepository.findFotosbyIdEquip(idEquipamento);
		ModelAndView modelAndView = new ModelAndView("/Home/Equipamento/ListarFotos");		
		modelAndView.addObject("fotosobj", foto);		
		return modelAndView;
	}
	
	@GetMapping("**/Home/Equipamento/EditarEquipamento/{idEquipamento}")
	public ModelAndView Editar(@PathVariable("idEquipamento") Long idEquipamento) {		
		Optional<Equipamento> equipamento = equipamentoRepository.findById(idEquipamento);		
		ModelAndView modelAndView = new ModelAndView("/Home/Equipamento/Cadastro");		
		modelAndView.addObject("equipamentoobj", equipamento.get());
		modelAndView.addObject("setores", setorRepository.findSetores());
		return modelAndView;
	}
	
	private static final String EXTERNAL_FILE_PATH = "C:/temp/";
	
	@RequestMapping("**/Home/Equipamento/FotoDownload/{fileName}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
	
	@GetMapping("**/Home/Equipamento/RemoverEquipamento/{idEquipamento}")
	public ModelAndView Excluir(@PathVariable("idEquipamento") Long idEquipamento, RedirectAttributes ra) {			
		Equipamento equipamento = equipamentoRepository.findEquipamentoByid(idEquipamento);
		equipamento.setDeletado(true);
		equipamentoRepository.save(equipamento);
		ra.addFlashAttribute("msg", "Equipamento Removido com sucesso");		
		ModelAndView modelAndView = new ModelAndView("redirect:/Home/Equipamento/ListarEquipamento");		
		return modelAndView;
	}
}
