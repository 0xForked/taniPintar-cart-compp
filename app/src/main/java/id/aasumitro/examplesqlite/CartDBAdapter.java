package id.aasumitro.examplesqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartDBAdapter {
    private Context context;
    private SQLiteDatabase db;
    private CartDBHelper helper;

    public CartDBAdapter(Context c) {
        this.context = c;
        helper= new CartDBHelper(c);
    }

    public void openDB()
    {
        try {
            db=helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB()
    {
        try {
            helper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public boolean add(final CartModel cartModel)
    {
        try {
            new AsyncTask<Void, Void, Long>(){
                @Override
                protected Long doInBackground(Void... voids) {

                    ContentValues values = new ContentValues();
                    values.put(CartContract.CartEntity.COLUMN_NAME_SELLER_ID, cartModel.getSellerId());
                    values.put(CartContract.CartEntity.COLUMN_NAME_SELLER_NAME, cartModel.getSellerName());
                    values.put(CartContract.CartEntity.COLUMN_NAME_ITEM_ID, cartModel.getItemId());
                    values.put(CartContract.CartEntity.COLUMN_NAME_ITEM_NAME, cartModel.getItemName());
                    values.put(CartContract.CartEntity.COLUMN_NAME_ITEM_IMAGE, cartModel.getItemImage());
                    values.put(CartContract.CartEntity.COLUMN_NAME_ITEM_DESCRIPTION, cartModel.getItemDesc());
                    values.put(CartContract.CartEntity.COLUMN_NAME_ITEM_PRICE, cartModel.getItemPrice());

                    return db.insert(CartContract.CartEntity.TABLE_NAME, null, values);
                }

                @Override
                protected void onPostExecute(Long status) {
                    Toast.makeText(context, "status row "+status, Toast.LENGTH_SHORT).show();
                }
            }.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CartModel> getAllRecord() {
        List<CartModel> cartList = new ArrayList<CartModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CartContract.CartEntity.TABLE_NAME;

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CartModel cartModel = new CartModel();
                cartModel.setId(Integer.parseInt(cursor.getString(0)));
                cartModel.setSellerId(cursor.getString(1));
                cartModel.setSellerName(cursor.getString(2));
                cartModel.setItemId(cursor.getString(3));
                cartModel.setItemName(cursor.getString(4));
                cartModel.setItemImage(cursor.getString(5));
                cartModel.setItemDesc(cursor.getString(6));
                cartModel.setItemPrice(cursor.getString(7));
                cartList.add(cartModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cartList;
    }

    public boolean delete(int id)
    {
        try {
            int result= db.delete(CartContract.CartEntity.TABLE_NAME, CartContract.CartEntity._ID + " = ?",
                    new String[] { String.valueOf(id) });
            if(result>0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCartCount() {
        String countQuery = "SELECT  * FROM " + CartContract.CartEntity.TABLE_NAME;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }

    public int getPriceCount() {

        try {
            String countQuery = "SELECT sum(item_price) FROM " + CartContract.CartEntity.TABLE_NAME;
            @SuppressLint("Recycle") Cursor cursor = db.rawQuery(countQuery, null);
            // return count
            if(cursor.moveToFirst())
            {
                return cursor.getInt(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
