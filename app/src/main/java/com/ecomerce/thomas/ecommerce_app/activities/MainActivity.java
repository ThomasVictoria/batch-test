package com.ecomerce.thomas.ecommerce_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.batch.android.Batch;
import com.batch.android.Config;
import com.ecomerce.thomas.ecommerce_app.R;
import com.ecomerce.thomas.ecommerce_app.lib.BatchSetup;
import com.ecomerce.thomas.ecommerce_app.lib.JsonData;
import com.ecomerce.thomas.ecommerce_app.model.Product;
import com.ecomerce.thomas.ecommerce_app.model.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BatchSetup.start();

        ListView mListView = (ListView) findViewById(R.id.listView);
        List<Product> products = new ArrayList<Product>();

        String jsonData = JsonData.GetData(MainActivity.this);

        // Try to parse JSON
        try {
            // Creating JSONObject from String
            JSONObject jsonObjMain = new JSONObject(jsonData);

            // Creating JSONArray from JSONObject
            JSONArray jsonArray = jsonObjMain.getJSONArray("products");


            // JSONArray has four JSONObject
            for (int i = 0; i < jsonArray.length(); i++) {

                // Creating JSONObject from JSONArray
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Getting data from individual JSONObject

                products.add(new Product(jsonObj.getString("name"), jsonObj.getString("description"), jsonObj.getString("price")));

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ProductAdapter adapter = new ProductAdapter(MainActivity.this, products);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Productactivity.class);
                Bundle b = new Bundle();
                b.putInt("id", position);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

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
