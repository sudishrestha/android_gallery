package np.com.sudishrestha.www.galleryview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Gallery> galleryList = new ArrayList<>();
    private RecyclerView mRecycleView;
    private galleryRecyclerAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecycleView = findViewById(R.id.idRecyclerViewHorizontalList2);
        recycleAdapter = new galleryRecyclerAdapter(galleryList, getApplicationContext(), true);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecycleView.setAdapter(recycleAdapter);
        populategroceryList();
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

        recycleAdapter.notifyDataSetChanged();
    }

}
