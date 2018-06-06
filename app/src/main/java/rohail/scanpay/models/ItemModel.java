package rohail.scanpay.models;

import java.io.Serializable;

public class ItemModel implements Serializable {

    //Sample json

    // {
    //  "name": "Yogurt",
    //  "description": "100% Natural yogurt",
    //  "price": "2.99",
    //  "currency": "pln"
    //}

    private String name;
    private String price;
    private String currency;
    private String description;
    private String count = "1";
    private double total;

    public double getTotal() {
        total = Double.parseDouble(getPrice()) * Double.parseDouble(getCount());
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
