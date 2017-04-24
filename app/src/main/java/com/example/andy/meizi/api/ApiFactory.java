package com.example.andy.meizi.api;

import static okhttp3.internal.Internal.instance;

/**
 * apiFactory
 * Created by andy on 17-4-21.
 */

public class ApiFactory {
    private static Api api = null;

    public static Api getApi() {
        synchronized (Api.class) {
            if (instance == null) {
                api = new Api();
            }
        }
        return api;
    }


}
