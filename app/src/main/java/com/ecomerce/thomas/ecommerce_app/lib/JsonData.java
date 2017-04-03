package com.ecomerce.thomas.ecommerce_app.lib;

import android.app.Activity;

import com.ecomerce.thomas.ecommerce_app.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thomas on 01/04/2017.
 */

public class JsonData {
    public static String GetData (Activity activity) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    public static Product loadData(String json, int id) {

        Product product = null;

        try {
            // Creating JSONObject from String
            JSONObject jsonObjMain = new JSONObject(json);

            // Creating JSONArray from JSONObject
            JSONArray jsonArray = jsonObjMain.getJSONArray("products");

            JSONObject jsonObj = jsonArray.getJSONObject(id);

            product = new Product(jsonObj.getString("name"), jsonObj.getString("description"), jsonObj.getString("price"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return product;

    }
}
