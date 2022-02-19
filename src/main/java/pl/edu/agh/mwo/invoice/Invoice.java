package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private List<Product> products = new ArrayList<>();

    private Map<Product,Integer> products = new HashMap<>(){

    };

    public void addProduct(Product product) {
        this.addProduct(product,1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity.equals(0)){
            throw new IllegalArgumentException("Product price cannot be null");
        }
        if ( quantity < 0){
            throw new IllegalArgumentException("Product price cannot be empty");
        }
        this.products.put(product,quantity);
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            sum = sum.add(product.getPrice().multiply(quantityAsBigDecimal));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            sum = sum.add(product.getPrice().multiply(product.getTaxPercent()).multiply(quantityAsBigDecimal));
        }
        return sum;    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()){
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            sum = sum.add(product.getPrice().multiply(product.getTaxPercent().add(BigDecimal.ONE)).multiply(quantityAsBigDecimal));
        }
        return sum;    }
}
