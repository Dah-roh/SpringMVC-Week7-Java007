package net.codejava.Repository;

import net.codejava.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByBrandAndName(String brand, String name);
    boolean existsProductByBrandAndName(String brand, String name);
}
