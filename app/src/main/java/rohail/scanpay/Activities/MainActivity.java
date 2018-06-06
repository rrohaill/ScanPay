package rohail.scanpay.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rohail.scanpay.R;
import rohail.scanpay.adapters.ListAdapter;
import rohail.scanpay.models.ItemModel;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 20;
    //qr code scanner object
    private IntentIntegrator qrScan;

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemModel> itemModelArrayList;
    private Button btnPay;
    private TextView tvTotal;
    private LinearLayout llBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //pay button
        btnPay = findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double sum = getSum();

                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("SUM", sum + "");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        tvTotal = findViewById(R.id.tv_total);
        llBottom = findViewById(R.id.ll_bottom);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        initAdapter();
    }

    private void initAdapter() {
        itemModelArrayList = new ArrayList<>();
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

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                llBottom.setVisibility(View.INVISIBLE);
                initAdapter();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                //if qrcode has nothing in it
                if (result.getContents() == null) {
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                } else {
                    //if qr contains data
                    try {
                        //converting the data to json
                        JSONObject obj = new JSONObject(result.getContents());
                        //setting values to textviews
                        ItemModel itemModel = new ItemModel();
                        itemModel.setName(obj.getString("name"));
                        itemModel.setPrice(obj.getString("price"));
                        itemModel.setCurrency(obj.getString("currency"));
                        itemModel.setDescription(obj.getString("description"));
//                        itemModelArrayList.add(itemModel);
                        showDialog(itemModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //if control comes here
                        //that means the encoded format not matches
                        //in this case you can display whatever data is available on the qrcode
                        //to a toast
                        Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void takePicture() {
        //initiating the qr code scan
        qrScan.initiateScan();
    }

    private void showDialog(final ItemModel itemModel) {

        TextView tvTitle;
        TextView tvDes;
        final TextInputEditText etCounter;
        ImageView ivRemove;
        ImageView ivAdd;

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        dialogBuilder.setView(dialogView);

        tvTitle = dialogView.findViewById(R.id.tv_title);
        tvDes = dialogView.findViewById(R.id.tv_description);
        etCounter = dialogView.findViewById(R.id.et_count);
        ivAdd = dialogView.findViewById(R.id.iv_add);
        ivRemove = dialogView.findViewById(R.id.iv_remove);

        tvTitle.setText(itemModel.getName()+" | Price: "+itemModel.getPrice()+" "+itemModel.getCurrency());
        tvDes.setText(itemModel.getDescription());
        etCounter.setText("1");
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(etCounter.getText().toString());
                if (!etCounter.getText().toString().isEmpty()) {
                    etCounter.setText((value + 1) + "");
                }
            }
        });
        ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(etCounter.getText().toString());
                if (!etCounter.getText().toString().isEmpty() && value > 0) {
                    etCounter.setText((value - 1) + "");
                }
            }
        });

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                if (!etCounter.getText().toString().isEmpty() && Integer.parseInt(etCounter.getText().toString()) > 0) {
                    itemModel.setCount(etCounter.getText().toString());
                    setView(itemModel);
                }

            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setTitle("Item Detail");
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_suggessions) {
            Intent intent = new Intent(this, SuggestionActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Double getSum() {
        Double sum = 0.0;
        for (int i = 0; i < itemModelArrayList.size(); i++) {
            sum += itemModelArrayList.get(i).getTotal();
        }
        return sum;
    }

    public void setView(ItemModel itemModel) {
        mAdapter.add(itemModelArrayList.size(), itemModel);
        tvTotal.setText("Total: " + getSum() + " PLN");
        llBottom.setVisibility(View.VISIBLE);
    }
}
