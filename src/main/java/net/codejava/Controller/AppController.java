package net.codejava.Controller;

import net.codejava.Models.Customer;
import net.codejava.Models.Product;
import net.codejava.Service.CustomerService;
import net.codejava.Service.ProductService;
import net.codejava.ServiceImpl.CustomerServiceImpl;
import net.codejava.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AppController {

	private ProductService service;
	private CustomerService customerService;
	//camelCase---- variable names
	//kebab-case-looks-like-suya-on-a-stick
	//snake_case_looks_like_a_snake ---routes or endpoint naming
	@Autowired
	public AppController(ProductService service, CustomerService customerService) {
		this.service = service;
		this.customerService = customerService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHomePage(ModelAndView modelAndView) {
		return customerService.loginCustomer(modelAndView);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView, HttpSession session) {
		return customerService.getCustomer(customer, modelAndView);
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@PostMapping(value = "/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
//		String message = "You have successfully added a product!!";
//		model.addAttribute("flash", message);
		return "redirect:/";
	}


	@GetMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int identity) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(identity);
		mav.addObject("product", product);
		
		return mav;
	}


	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}
}
