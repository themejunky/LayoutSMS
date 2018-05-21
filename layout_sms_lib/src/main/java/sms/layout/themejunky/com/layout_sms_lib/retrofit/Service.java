package sms.layout.themejunky.com.layout_sms_lib.retrofit;


import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Service instance;
    private InterfaceWs mInterfaceWs;

    public synchronized static Service getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            return instance = new Service(context);
        }
    }

    private Service(Context context) {

      //  int cacheSize = 10 * 1024 * 1024; // 10 MB
       // Cache cache = new Cache(context.getCacheDir(), cacheSize);

       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        /*  //.cache(cache)*/

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://mobdash.crave.ro")
                .build();

        mInterfaceWs = retrofit.create(InterfaceWs.class);
    }

    public InterfaceWs maingetInterface() {
        return mInterfaceWs;
    }
}
