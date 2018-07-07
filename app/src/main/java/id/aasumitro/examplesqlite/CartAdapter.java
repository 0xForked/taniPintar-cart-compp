package id.aasumitro.examplesqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartHolder> {
    private List<CartModel> items;
    private int itemLayout;
    private Context context;

    public CartAdapter(List<CartModel> items, int itemLayout, Context c){
        this.context = c;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new CartHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CartModel item = items.get(position);
        holder.mItemName.setText(item.getItemName());
        holder.mItemPrice.setText(item.getItemPrice());
        Picasso.get()
                .load(item.getItemImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.mItemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int position) {
        CartModel i = items.get(position);
        int id = i.getId();

        CartDBAdapter db = new CartDBAdapter(context);
        db.openDB();
        if(db.delete(id)) {
            items.remove(position);
        } else {
            Toast.makeText(context,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();
        notifyItemRemoved(position);
    }
}
