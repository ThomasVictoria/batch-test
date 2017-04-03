package com.ecomerce.thomas.ecommerce_app.lib;

import com.batch.android.Batch;
import com.batch.android.Config;

/**
 * Created by thomas on 03/04/2017.
 */

public class BatchSetup {

    public static void start() {
        Batch.setConfig(new Config("BATCH-API-KEY"));
        Batch.Push.setGCMSenderId("GCM-SENDER-ID");
    }

}
