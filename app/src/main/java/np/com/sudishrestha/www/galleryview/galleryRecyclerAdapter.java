package np.com.sudishrestha.www.galleryview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class galleryRecyclerAdapter extends RecyclerView.Adapter<galleryRecyclerAdapter.GroceryViewHolder> {
    private List<Gallery> horizontalGrocderyList;
    Context context;
    Boolean dataType = true;

    public galleryRecyclerAdapter(List<Gallery> horizontalGrocderyList, Context context, Boolean type) {
        this.horizontalGrocderyList = horizontalGrocderyList;
        this.dataType = type;
        this.context = context;
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);

        GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, final int position) {
        holder.imageView.setImageResource(horizontalGrocderyList.get(position).getImage());
        holder.txtview.setText(horizontalGrocderyList.get(position).getName());

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GalleryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", position + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalGrocderyList.size();
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtview;

        public GroceryViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.idProductImage);
            txtview = view.findViewById(R.id.idProductName);


        }
    }
}