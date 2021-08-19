package net.codejava.ServiceImpl;

import net.codejava.Models.Customer;
import net.codejava.Repository.CustomerRepository;
import net.codejava.Service.CustomerService;
import net.codejava.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CustomerServiceImpl implements CustomerService {
    private ProductService productService;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ProductService productService, CustomerRepository customerRepository) {
        this.productService = productService;
        this.customerRepository = customerRepository;
    }

    @Override
    public ModelAndView loginCustomer(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @Override
    public ModelAndView getCustomer(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView) {
        Customer customer1 = customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (customer1!=null) {
           return productService.listAll(modelAndView);
        }  else
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Incorrect password or username");
        return modelAndView;
    }
}
