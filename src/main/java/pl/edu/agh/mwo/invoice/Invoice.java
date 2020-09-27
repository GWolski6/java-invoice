package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products = new ArrayList<>();
	
	private  BigDecimal tax = BigDecimal.ZERO ;
	private  BigDecimal total = BigDecimal.ZERO ;

	public void addProduct(Product product) {
		if(product != null) products.add(product);
	}

	public void addProduct(Product product, Integer quantity) {
		
		if(quantity == null || quantity <= 0) throw new IllegalArgumentException();
		for(int i = 0; i < quantity; i++)
		{
			this.addProduct(product);
		}
		
	}

	public BigDecimal getSubtotal() {
		
		BigDecimal subtotal = BigDecimal.ZERO ;
		
		if(products != null && products.size() > 0) {
		for(Product product : products)
		{
			subtotal = subtotal.add(product.getPrice());
		}
		}
		
		return subtotal;
	}

	public BigDecimal getTax() {
		
		for(Product product : products)
		{
			this.tax = tax.add(product.getPrice().multiply(product.getTaxPercent()));
		}
		
		return this.tax;
	}

	public BigDecimal getTotal() {
		return this.total;
	}
}
