package np.com.sudishrestha.www.galleryview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private List<Gallery> galleryList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerViewHorizontalListAdapter galleryAdapter;
    SharedPreferences.Editor editor;
    private List<Gallery> galleryListThumb = new ArrayList<>();
    private RecyclerView mRecyclerViewThumb;
    private RecyclerViewHorizontalListAdapter galleryAdapterThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mRecyclerView = findViewById(R.id.idRecyclerViewHorizontalList);
        mRecyclerViewThumb = findViewById(R.id.idRecyclerViewHorizontalList1);
        String position = getIntent().getStringExtra("position");
        int defaultPosition = Integer.parseInt(position);
        editor = getSharedPreferences("gallery_position", MODE_PRIVATE).edit();
        editor.putInt("position", defaultPosition);
        editor.apply();

        galleryAdapter = new RecyclerViewHorizontalListAdapter(galleryList, getApplicationContext(), true, mRecyclerView, mRecyclerViewThumb, GalleryActivity.this);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(GalleryActivity.this, LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        snapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(galleryAdapter);

        galleryAdapterThumb = new RecyclerViewHorizontalListAdapter(galleryListThumb, getApplicationContext(), false, mRecyclerView, mRecyclerViewThumb, GalleryActivity.this);
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(GalleryActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewThumb.setLayoutManager(horizontalLayoutManager1);
        mRecyclerViewThumb.setAdapter(galleryAdapterThumb);
        populategroceryList();

        mRecyclerView.scrollToPosition(defaultPosition);
        mRecyclerViewThumb.scrollToPosition(defaultPosition);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = getCurrentItem(mRecyclerView);
                    editor = getSharedPreferences("gallery_position", MODE_PRIVATE).edit();
                    editor.putInt("position", position);
                    editor.apply();
                    Log.e("Current Position is ", position + "");
                    galleryAdapterThumb.notifyDataSetChanged();
                }
            }
        });


    }

    private int getCurrentItem(RecyclerView mRecyclerView) {
        return ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void populategroceryList() {
        Gallery one = new Gallery("one", R.drawable.one);
        Gallery two = new Gallery("two", R.drawable.two);
        Gallery three = new Gallery("three", R.drawable.three);
        Gallery four = new Gallery("four", R.drawable.four);
        Gallery five = new Gallery("five", R.drawable.five);
        Gallery six = new Gallery("six", R.drawable.six);
        Gallery seven = new Gallery("seven", R.drawable.seven);
        Gallery eight = new Gallery("five", R.drawable.eight);
        Gallery nine = new Gallery("six", R.drawable.nine);
        Gallery ten = new Gallery("seven", R.drawable.ten);

        galleryList.add(one);
        galleryList.add(two);
        galleryList.add(three);
        galleryList.add(four);
        galleryList.add(five);
        galleryList.add(six);
        galleryList.add(seven);
        galleryList.add(eight);
        galleryList.add(nine);
        galleryList.add(ten);

        galleryListThumb.add(one);
        galleryListThumb.add(two);
        galleryListThumb.add(three);
        galleryListThumb.add(four);
        galleryListThumb.add(five);
        galleryListThumb.add(six);
        galleryListThumb.add(seven);
        galleryListThumb.add(eight);
        galleryListThumb.add(nine);
        galleryListThumb.add(ten);


        galleryAdapter.notifyDataSetChanged();
        galleryAdapterThumb.notifyDataSetChanged();
    }

}
