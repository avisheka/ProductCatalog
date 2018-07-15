package com.retail.globomart.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.retail.globomart.model.Products;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DataJpaTest
public class ProductJpaRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IProductJpaRepository productJpaRepository;
	
	@Test
	public void addProduct1() {
		Products product = new Products();
		product.setName("Pixel");
		product.setType("mobile");
		
		Products prodSavedinJpa = entityManager.persist(product);
		Products getProdfromJpa = productJpaRepository.getOne(prodSavedinJpa.getId());
		
		assertThat(getProdfromJpa).isEqualTo(prodSavedinJpa);
	}
	
	@Test
	public void addProduct2() {
		Products product = new Products();
		product.setName("IPhone-X");
		product.setType("mobile");
		
		Products prodSavedinJpa = entityManager.persist(product);
		Products getProdfromJpa = productJpaRepository.getOne(prodSavedinJpa.getId());
		
		assertThat(getProdfromJpa).isEqualTo(prodSavedinJpa);
	}
	
	@Test
	public void addProduct3() {
		Products product = new Products();
		product.setName("Mac-Pro");
		product.setType("laptop");
		
		Products prodSavedinJpa = entityManager.persist(product);
		Products getProdfromJpa = productJpaRepository.getOne(prodSavedinJpa.getId());
		
		assertThat(getProdfromJpa).isEqualTo(prodSavedinJpa);
	}
	
	@Test
	public void getAllProducts() {
		addProduct1();
		addProduct2();
		addProduct3();
		List<Products> productsfromJpa = productJpaRepository.findAll();
		assertThat(productsfromJpa.size()).isEqualTo(3);
	}
	
	@Test
	public void getProductsByType() {
		addProduct1();
		addProduct2();
		addProduct3();
		List<Products> productsfromJpa = productJpaRepository.findByType("laptop");
		assertNotNull(productsfromJpa);
		assertThat(productsfromJpa.get(0).getType()).isEqualTo("laptop");
	}
	
	@Test
	public void removeProduct() {
		addProduct1();
		addProduct2();
		addProduct3();
		productJpaRepository.deleteById(productJpaRepository.findByType("laptop").get(0).getId());
		assertNotNull(productJpaRepository.findAll());
		assertThat(productJpaRepository.findAll().size()).isEqualTo(2);
	}
	
}
