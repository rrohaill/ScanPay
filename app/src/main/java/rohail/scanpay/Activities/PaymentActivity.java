package rohail.scanpay.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rohail.scanpay.R;

public class PaymentActivity extends AppCompatActivity {

    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvTotal = findViewById(R.id.tv_total);
        tvTotal.setText(getIntent().getStringExtra("SUM") + " PLN");
    }


    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", MainActivity.REQUEST_CODE);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
