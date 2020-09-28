package pl.edu.agh.mwo.invoice; // WOW lets do this

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;
    
    public void addProduct(Product product) {
        if (this.products.containsKey(product)) {
            products.put(product, this.products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }
    
    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        
        if (this.products.containsKey(product)) {
            products.put(product, this.products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
    
    public int getNumber() {
        return number;
    }
    
    public Map<Product, Integer> getProducts() {
        return products;
    }
    
    public String invoicePrinter() {
        String invoiceLook = "Invoice number: " + this.number + "\n";
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            invoiceLook += "Products: " + entry.getKey().getName()
                + ", Quantity: " 
                + entry.getValue() 
                + ", Price: " 
                + entry.getKey().getPrice() + "\n";
        }
        invoiceLook += "Number of postitions: " + products.size();
        return invoiceLook;
    }
    
}
