package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<>();
	
	public void addProduct(Product product) {
		this.addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		this.products.put(product, quantity);
	}

	public BigDecimal getNetPrice() {
		
		BigDecimal sum = new BigDecimal(0);
		for (Product product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
		}
		
		return sum;
	}

	public BigDecimal getTax() {
		return new BigDecimal(0);
	}

	public BigDecimal getGrossPrice() {
		return new BigDecimal(0);
	}
}
