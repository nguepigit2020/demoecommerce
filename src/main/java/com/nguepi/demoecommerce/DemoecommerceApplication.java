package com.nguepi.demoecommerce;

import com.nguepi.demoecommerce.dao.CategoryRepository;
import com.nguepi.demoecommerce.dao.ProductRepository;
import com.nguepi.demoecommerce.entities.Category;
import com.nguepi.demoecommerce.entities.Product;
import com.nguepi.demoecommerce.web.CatalogueController;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class DemoecommerceApplication implements CommandLineRunner {
    @Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
    @Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(DemoecommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
           repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

		   categoryRepository.save(new Category(null,"Computer",null,null,null));
		   categoryRepository.save(new Category(null,"Printers",null,null,null));
		   categoryRepository.save(new Category(null,"Smart phone",null,null,null));
		   Random rnd = new Random();
		   categoryRepository.findAll().forEach(c ->{
			   for (int i=0; i<10; i++) {
				   Product p = new Product();
				   p.setName(RandomString.make(18));
				   p.setCurrentPrice(100 + rnd.nextInt(10000));
				   p.setAvailable(rnd.nextBoolean());
				   p.setPromotion(rnd.nextBoolean());
				   p.setSelected(rnd.nextBoolean());
				   p.setCategory(c);
				   p.setPhotoName("unknown.png");
				   productRepository.save(p);
			   }
		   });
	}
}
