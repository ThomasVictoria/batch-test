package com.ecomerce.thomas.ecommerce_app.activities;

import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.batch.android.Batch;
import com.ecomerce.thomas.ecommerce_app.R;
import com.ecomerce.thomas.ecommerce_app.lib.BatchSetup;
import com.ecomerce.thomas.ecommerce_app.lib.JsonData;
import com.ecomerce.thomas.ecommerce_app.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Productactivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productactivity);

        BatchSetup.start();

        Bundle b = getIntent().getExtras();
        int id; // or other values
        if(b != null) {
            id = b.getInt("id");

            String jsonData = JsonData.GetData(Productactivity.this);
            Product product = JsonData.loadData(jsonData, id);

            injectData(product);

            Button payment = (Button) findViewById(R.id.payment);

            payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Productactivity.this, Paymentactivity.class);
                    String jsonData = JsonData.GetData(Productactivity.this);
                    Bundle b = getIntent().getExtras();
                    if(b != null) {
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void injectData(Product product){
        TextView title = (TextView) findViewById(R.id.title);
        TextView desc = (TextView) findViewById(R.id.desc);
        TextView price = (TextView) findViewById(R.id.price);

        title.setText(product.getName());
        desc.setText(product.getDescription());
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
