package edu.illinois.cs.cs125.spring2019.lab12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import  android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    /**
     * button.
     */
    private Button generate;
    /**
     * advice.
     */
    public static String a = "";

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        generate = findViewById(R.id.button2);
        generate.setOnClickListener(v -> {
            Log.d(TAG, "Button Clicked");
            startAPICall("advice");
            Intent intent = new Intent(MainActivity.this, News.class);
            //intent.putExtra("advice", a);
            startActivity(intent);
        });
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    void startAPICall(final String ipAddress) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.adviceslip.com/advice",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            try {
                                JSONObject slip = response.getJSONObject("slip");
                                a = slip.getString("advice");
                                Log.d(TAG, a);
                                //Intent intent = new Intent(MainActivity.this, News.class);
                                //intent.putExtra("advice", a);
                                //startActivity(intent);
                            } catch (JSONException e) {
                                Log.d(TAG, "ERROR JSON FILE PARSED INCORRECTLY");
                            }

                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
            Object ip = "192.17.96.8";
        } catch (JSONException ignored) { }
    }
}
