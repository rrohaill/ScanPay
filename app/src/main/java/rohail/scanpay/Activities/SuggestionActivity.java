package rohail.scanpay.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import rohail.scanpay.R;
import rohail.scanpay.adapters.ListAdapter;
import rohail.scanpay.models.ItemModel;

public class SuggestionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemModel> itemModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
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

        itemModel.setName("Flour");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Apple");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Eggs");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Bread");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Milk");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Butter");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Onion");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Coke");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Biscuits");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Chips");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Cheese");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Rice");

        itemModelArrayList.add(itemModel);

        itemModel = new ItemModel();
        itemModel.setName("Sugar");

        itemModelArrayList.add(itemModel);

    }

}
