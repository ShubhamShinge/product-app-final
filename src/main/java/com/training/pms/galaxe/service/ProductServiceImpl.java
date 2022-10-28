package com.training.pms.galaxe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productdao;
	
	@Override
	public String saveProduct(Product product) {
		// TODO Auto-generated method stub
		if(product.getPrice()<0 | product.getQuantityOnHand()<0) {
			return "Product price or QOH cannot be negative. Not Saved";
		}
		else {
			productdao.save(product);
			return "Product Saved Succesfully";
		}
		
	}

	@Override
	public String updateProduct(Product product) {
		if(product.getPrice() <0 | product.getQuantityOnHand() <0)
		{
			return "Product price or QOH cannot be negative. Not Updated";
		}
		else {
			productdao.save(product);
			return "Product Updated Successfully";
		}

	}

	@Override
	public String deleteProduct(int productId) {
		productdao.deleteById(productId);
		return "Product deleted successfully";

	}

	@Override
	public Product getProduct(int productId) {
		Optional<Product> product= productdao.findById(productId);
		return product.get();
	}

	@Override
	public List<Product> getProduct() {
		return (List<Product>) productdao.findAll();
	}

	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> product= productdao.findById(productId);
		return product.isPresent();
	}


	@Override
	public List<Product> searchProduct(String productName) {
		// TODO Auto-generated method stub
		return productdao.findByProductName(productName);
	}

	@Override
	public List<Product> searchProduct(String productName, int price, int qoh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchProduct(int min, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> checkStockStatus(int minStock) {
		// TODO Auto-generated method stub
		return productdao.findByQuantityOnHandGreaterThanEqual(minStock);
	}

}
