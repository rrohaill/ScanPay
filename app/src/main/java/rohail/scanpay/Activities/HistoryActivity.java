package rohail.scanpay.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import rohail.scanpay.R;
import rohail.scanpay.adapters.ListAdapter;
import rohail.scanpay.models.ItemModel;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemModel> itemModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initAdapter();
    }

    private void initAdapter() {
        itemModelArrayList = new ArrayList<>();
        initDummy(itemModelArrayList);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(this, itemModelArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDummy(ArrayList<ItemModel> itemModelArrayList) {
        ItemModel itemModel = new ItemModel();

        itemModel.setName("Monday, 01-02-2018");
        itemModel.setPrice("35.29");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Tuesday, 01-02-2018");
        itemModel.setPrice("152.35");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Monday, 01-02-2018");
        itemModel.setPrice("12.58");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Friday, 01-02-2018");
        itemModel.setPrice("69.56");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Friday, 01-02-2018");
        itemModel.setPrice("35.29");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Sunday, 01-02-2018");
        itemModel.setPrice("85.52");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Sunday, 01-02-2018");
        itemModel.setPrice("98.78");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Saturday, 01-02-2018");
        itemModel.setPrice("58.87");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Monday, 01-02-2018");
        itemModel.setPrice("33.33");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Wednesday, 01-02-2018");
        itemModel.setPrice("375.29");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Sunday, 01-02-2018");
        itemModel.setPrice("55.97");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Monday, 01-02-2018");
        itemModel.setPrice("35.29");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Monday, 01-02-2018");
        itemModel.setPrice("35.29");

        itemModelArrayList.add(itemModel);

    }
}
