package com.training.pms.galaxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
import com.training.pms.galaxe.service.ProductServiceImpl;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl	productServiceImpl;
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping					//http://localhost:9090/product
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productServiceImpl.getProduct();
				
		ResponseEntity<List<Product>> responseEntity;

		if(products.isEmpty()) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return responseEntity;
	}
	@GetMapping("{productId}")
	public Product getProduct(@PathVariable("productId")Integer productId) {
		return productServiceImpl.getProduct(productId);
	}
	@DeleteMapping("{productId}")
	public String deleteProduct(@PathVariable("productId")Integer productId) {
		return "Deleting a single product with product id:"+productId;
	}
//	@PostMapping()
//	public String saveProduct(@RequestBody Product product) {
//		return productServiceImpl.saveProduct(product);
//		
//		
//	}
	@PostMapping()					//http://localhost:9090/product/		-POST		-BODY (product) 102
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(productServiceImpl.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" already exists", HttpStatus.CONFLICT);
		}
		else
		{
			String message = productServiceImpl.saveProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	@PutMapping()
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity;
		int pId = product.getProductId();
		if(!productServiceImpl.isProductExists(pId)) {
			responseEntity = new ResponseEntity<String>("Product with product id :"+pId+" Doesn't Exists", HttpStatus.NOT_FOUND);
		}
		else
		{
			String message = productServiceImpl.updateProduct(product);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;

		
	}
	
	@GetMapping("searchByProductName/{productName}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable("productName")String productName) {
		ResponseEntity<List<Product>> responseEntity;
		List<Product> products=productServiceImpl.searchProduct(productName);
		if(products.isEmpty()) {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
		}
		else {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("checkStockStatus/{minStock}")
	public ResponseEntity<List<Product>> getStockStatus(@PathVariable("minStock")Integer minStock){
		ResponseEntity<List<Product>> responseEntity;
		List<Product> products=productServiceImpl.checkStockStatus(minStock);
		if(products.isEmpty()) {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
		}
		else {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		return responseEntity;
	}
	@GetMapping("SearchByRange/{min}/{max}")
	public ResponseEntity<List<Product>> getProductByRange(@PathVariable("min")Integer min,@PathVariable("max")Integer max){
		ResponseEntity<List<Product>> responseEntity;
		List<Product> products=productServiceImpl.searchProduct(min,max);
		if(products.isEmpty()) {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
		}
		else {
			responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		}
		return responseEntity;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
