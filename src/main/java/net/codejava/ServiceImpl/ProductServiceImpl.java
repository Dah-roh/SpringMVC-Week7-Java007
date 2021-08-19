package net.codejava.ServiceImpl;

import java.util.List;

import net.codejava.Models.Product;
import net.codejava.Repository.ProductRepository;
import net.codejava.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;


	@Override
	public ModelAndView listAll(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		List<Product> listOfProducts = repo.findAll();
		modelAndView.addObject("listProducts",listOfProducts);
		modelAndView.addObject("justAString", "Hello World");
		return modelAndView;
	}
	@Override
	public void save(Product product) {
		repo.save(product);
	}
	@Override
	public Product get(long id) {
		return repo.findById(id).get();
	}
	@Override
	public void delete(long id) {
		repo.deleteById(id);
	}
}
