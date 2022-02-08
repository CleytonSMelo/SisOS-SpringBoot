package sisos.springboot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sisos.springboot.model.Setor;
import sisos.springboot.model.Usuarios;
import sisos.springboot.repository.SetorRepository;
import sisos.springboot.repository.UsuarioRepository;
import sisos.springboot.security.ImplementacaoUserDetailService;

@Controller
public class UploadControler {

	@RequestMapping(method = RequestMethod.GET , value = "/Home/Upload/Cadastro")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/Home/Upload/Cadastro");		
		return modelAndView ;
	}
	
	private static String UPLOADED_FOLDER = "C://temp//";
	
	@PostMapping("/Home/Upload/salvar")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("msgErro", "Por Favor Selecione um Arquivo para o upload");
            return "redirect:/Home/Upload/Cadastro";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            String url = path.toUri().getPath();
            System.out.println(url);
            
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("msg",
                    "upload realizado com sucesso '" + file.getOriginalFilename() + "'");
            
        } catch (IOException e) {
//        	redirectAttributes.addFlashAttribute("msgErro",
//                    "Tamanho maximo de 2MB excedido");
//        	return "redirect:/Home/Upload/Cadastro";
        }

        return "redirect:/Home/Upload/Cadastro";
    }
	
	
	private static final String EXTERNAL_FILE_PATH = "C:/temp/";

	@RequestMapping("**/Home/Upload/Download/{fileName:.+}")
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
			 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
	
	
	    
}
