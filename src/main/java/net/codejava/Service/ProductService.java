package net.codejava.Service;

import net.codejava.Models.Product;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService {
    ModelAndView listAll(ModelAndView modelAndView);
    void save(Product product);
    Product get(long id);
    void delete(long id);
}
