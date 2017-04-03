package com.ecomerce.thomas.ecommerce_app.activities;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.batch.android.Batch;
import com.ecomerce.thomas.ecommerce_app.R;
import com.ecomerce.thomas.ecommerce_app.lib.BatchSetup;
import com.ecomerce.thomas.ecommerce_app.lib.JsonData;
import com.ecomerce.thomas.ecommerce_app.model.Product;

import org.w3c.dom.Text;

public class Paymentactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentactivity);

        BatchSetup.start();

        Bundle b = getIntent().getExtras();
        int id; // or other values
        if(b != null) {
            id = b.getInt("id");

            String jsonData = JsonData.GetData(Paymentactivity.this);
            Product product = JsonData.loadData(jsonData, id);

            injectProduct(product);

            Button payment = (Button) findViewById(R.id.acheter);
            payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Paymentactivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    public void injectProduct(Product product) {
        TextView name = (TextView) findViewById(R.id.product);
        TextView price = (TextView) findViewById(R.id.price);

        name.setText(product.getName());
        price.setText(product.getPrice());
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Batch.onStart(this);
    }

    @Override
    protected void onStop()
    {
        Batch.onStop(this);

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        Batch.onDestroy(this);

        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        Batch.onNewIntent(this, intent);

        super.onNewIntent(intent);
    }
}
