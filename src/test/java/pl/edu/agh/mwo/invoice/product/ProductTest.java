package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.Product;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }
    
    @Test
    public void testExciseWithOtherProduct()
    {
        Product product = new OtherProduct("Chorizo", new BigDecimal("6"));
        
        Assert.assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(product.getExcise()));
    }

    @Test
    public void testExciseWithTaxFreeProduct()
    {    
        Product product = new TaxFreeProduct("Darmowe telewizory", new BigDecimal("1"));
        
        Assert.assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(product.getExcise()));
        Assert.assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(product.getTaxPercent()));
    }
    
    @Test
    public void testAll$$$ForFuel()
    {
        Product product = new FuelCanister("Ropa", new BigDecimal("66"));
        
        Assert.assertThat(new BigDecimal("5.56"), Matchers.comparesEqualTo(product.getExcise()));
        Assert.assertThat(BigDecimal.ZERO, Matchers.comparesEqualTo(product.getTaxPercent()));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
    }
    
    @Test
    public void testAll$$$ForWine()
    {
        Product product = new BottleOfWine("Riesling", new BigDecimal("68"));
        
        Assert.assertThat(new BigDecimal("5.56"), Matchers.comparesEqualTo(product.getExcise()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
        Assert.assertThat(new BigDecimal("68"), Matchers.comparesEqualTo(product.getPrice()));
    }
    
    @Test
    public void testPriceForFuel()
    {
        final BigDecimal priceWithTax = new BigDecimal("80").add(new BigDecimal("80").multiply(BigDecimal.ZERO)).add(new BigDecimal("5.56"));
        Product product = new FuelCanister("Paliwo", new BigDecimal("80"));
        
        Assert.assertThat(priceWithTax, Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
    
    @Test
    public void testPriceForWine()
    {
        final BigDecimal priceWithTax = new BigDecimal("5").add(new BigDecimal("5").multiply(new BigDecimal("0.23"))).add(new BigDecimal("5.56"));
        Product product = new BottleOfWine("Amarena", new BigDecimal("5"));
        
        Assert.assertThat(priceWithTax, Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
}
