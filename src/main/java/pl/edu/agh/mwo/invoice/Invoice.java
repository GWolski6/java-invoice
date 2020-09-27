package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products = new ArrayList<>();

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
		
		BigDecimal tax = BigDecimal.ZERO ;
		
		for(Product product : products)
		{
			tax = tax.add(product.getPrice().multiply(product.getTaxPercent()));
		}
		
		return tax;
	}

	public BigDecimal getTotal() {
		
		return getSubtotal().add(getTax());
	}
}
