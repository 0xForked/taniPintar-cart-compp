package id.aasumitro.examplesqlite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CartHolder extends RecyclerView.ViewHolder {

    public TextView mItemName, mItemPrice;
    public ImageView mItemImage;

    public CartHolder(View itemView) {
        super(itemView);
        mItemName = itemView.findViewById(R.id.itemName);
        mItemPrice = itemView.findViewById(R.id.itemPrice);
        mItemImage = itemView.findViewById(R.id.itemImage);
    }
}
