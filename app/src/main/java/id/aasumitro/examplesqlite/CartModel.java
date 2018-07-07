package id.aasumitro.examplesqlite;

public class CartModel {
    int id;
    private String seller_name;
    private String seller_id;
    private String item_id;
    private String item_name;
    private String item_image;
    private String item_desc;
    private String item_price;

    public CartModel () { }

    public CartModel(int id, String seller_id, String seller_name,
                     String item_id, String item_name, String item_desc,
                     String item_price) {

        this.id = id;
        this.seller_id = seller_id;
        this.seller_name = seller_name;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_desc = item_desc;
        this.item_price = item_price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerId() {
        return seller_id;
    }

    public void setSellerId(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSellerName() {
        return seller_name;
    }

    public void setSellerName(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getItemId() {
        return item_id;
    }

    public void setItemId(String item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getItemImage() {
        return item_image;
    }

    public void setItemImage(String item_image) {
        this.item_image = item_image;
    }

    public String getItemDesc() {
        return item_desc;
    }

    public void setItemDesc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getItemPrice() {
        return item_price;
    }

    public void setItemPrice(String item_price) {
        this.item_price = item_price;
    }

}
