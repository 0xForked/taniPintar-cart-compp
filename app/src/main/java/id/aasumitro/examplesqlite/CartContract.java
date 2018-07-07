package id.aasumitro.examplesqlite;

import android.provider.BaseColumns;

public class CartContract {

    private CartContract() { }

    public static class CartEntity implements BaseColumns
    {

        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_SELLER_ID = "seller_id";
        public static final String COLUMN_NAME_SELLER_NAME = "seller_name";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_ITEM_NAME = "item_name";
        public static final String COLUMN_NAME_ITEM_IMAGE = "item_image";
        public static final String COLUMN_NAME_ITEM_DESCRIPTION = "item_desc";
        public static final String COLUMN_NAME_ITEM_PRICE = "item_price";

    }

}
