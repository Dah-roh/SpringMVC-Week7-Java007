package net.codejava.Service;

import net.codejava.Models.Customer;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {
    ModelAndView loginCustomer(ModelAndView modelAndView);
    ModelAndView getCustomer(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView);
}
