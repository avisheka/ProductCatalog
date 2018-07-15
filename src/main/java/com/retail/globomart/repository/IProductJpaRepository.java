package com.retail.globomart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.globomart.model.Products;

@Repository
public interface IProductJpaRepository extends JpaRepository<Products, Long>{

	public List<Products> findByType(String type);
}
