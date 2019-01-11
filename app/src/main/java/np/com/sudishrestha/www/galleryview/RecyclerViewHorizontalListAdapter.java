package np.com.sudishrestha.www.galleryview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewHorizontalListAdapter extends RecyclerView.Adapter<RecyclerViewHorizontalListAdapter.GroceryViewHolder> {
    private List<Gallery> horizontalGrocderyList;
    Context context;
    Boolean dataType = true;
    RecyclerView recyclerView1, recyclerView2;
    Activity myActivity;

    public RecyclerViewHorizontalListAdapter(List<Gallery> horizontalGrocderyList, Context context, Boolean type, RecyclerView rv1, RecyclerView rv2, Activity parentActivity) {
        this.horizontalGrocderyList = horizontalGrocderyList;
        this.dataType = type;
        this.context = context;
        this.recyclerView1 = rv1;
        this.recyclerView2 = rv2;
        this.myActivity = parentActivity;
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groceryProductView;
        if (dataType) {
            groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidingimages_layout, parent, false);
        } else {
            groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_imager1, parent, false);
        }
        GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, final int position) {
        holder.imageView.setImageResource(horizontalGrocderyList.get(position).getImage());
        holder.txtview.setText(horizontalGrocderyList.get(position).getName());
        SharedPreferences prefs = context.getSharedPreferences("gallery_position", MODE_PRIVATE);
        int idName = prefs.getInt("position", 0); //0 is the default value.
        if (!dataType) {
            if (idName == position) {
                holder.relativeLayout.setBackgroundResource(R.drawable.border_square);
            } else {

                holder.relativeLayout.setBackgroundResource(0);
            }
        } else {
            recyclerView2.scrollToPosition(position);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = context.getSharedPreferences("gallery_position", MODE_PRIVATE).edit();
                editor.putInt("position", position);
                editor.apply();
                recyclerView1.scrollToPosition(position);
                notifyDataSetChanged();
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(myActivity, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(myActivity);
                }
                builder.setTitle("YPO Gallery")
                        .setMessage("Are you sure you want to download the image?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(myActivity, " Download function here", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
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
        RelativeLayout relativeLayout;

        public GroceryViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.idProductImage);
            txtview = view.findViewById(R.id.idProductName);
            relativeLayout = view.findViewById(R.id.itembg);
        }
    }
}