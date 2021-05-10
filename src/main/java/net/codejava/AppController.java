package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	
	@RequestMapping("/")
	public ModelAndView viewHomePage(Model model) {
		ModelAndView modelAndView = new ModelAndView("index");
		List<Product> listProducts = service.listAll();
		modelAndView.addObject("listProducts",listProducts);
//		model.addAttribute("listProducts", listProducts);
//		return "index";
		return modelAndView;
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, Model model) {
		service.save(product);
//		String message = "You have successfully added a product!!";
//		model.addAttribute("flash", message);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
