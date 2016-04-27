package com.marskyer.tuski.moonfinder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class learn extends AppCompatActivity implements View.OnClickListener {


    TextView textView;
    EditText editText;
    RequestQueue requestQueue;
    Button button;
    public String myUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azimuth_view);

        editText=(EditText)findViewById(R.id.city);
        textView = (TextView) findViewById(R.id.text);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(this);


    }


    @Override
    public void onClick(View v) {

        String text=(String)editText.getText().toString();
        final String QUERY_PARAM="q";

        Uri.Builder builder=new Uri.Builder();
        builder.scheme("http")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendPath("daily")
                .appendQueryParameter(QUERY_PARAM,text)
                .appendQueryParameter("appid", "b51689c23cd565c10f73bfd9bc84b3ce");

        myUrl= builder.build().toString();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code, main, description, name, country, pressure, humidity;
                        //List<weather> weatherList = new ArrayList<weather>();


                        try {
                            //textView2.setText(null);
                            JSONObject place = response.getJSONObject("city");
                            name = place.getString("name");
                            country = place.getString("country");
                            textView.setText("Name: " + name + "  Country: " + country + "\n\n");
                            JSONArray jsonArray = response.getJSONArray("list");
                            for (int j = 0; j < jsonArray.length(); j++) {

                                JSONObject list = jsonArray.getJSONObject(j);
                                pressure = list.getString("pressure");
                                humidity = list.getString("humidity");
                                JSONArray jsonArray1 = list.getJSONArray("weather");

                                // for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject weather = jsonArray1.getJSONObject(0);



                                code = weather.getString("id");
                                main = weather.getString("main");
                                description = weather.getString("description");
                                //weatherList.add(new weather(main, description,humidity));

                                //weatherList.add(new weather(id,main,description));

                                textView.append("Day: "+code + " " + " " + main + "  " + "  " + description+"    "+pressure+"    "+humidity+" \n\n");

                                // }

                            }
                            //textView.setText((CharSequence) weatherList);
                           /* weatherAdapter adapter=new weatherAdapter(this, (ArrayList<weather>) weatherList);
                            listView.setAdapter(adapter);*/
                            //weatherAdapter wAdapter =new weatherAdapter(getApplicationContext(), (ArrayList<weather>) weatherList);
                            //listView.setAdapter(wAdapter);

                        } catch (Exception e) {
                            //e.printStackTrace();
                            //textView2.setText(" "+myUrl+"\n\n" );
                            Toast.makeText(getApplicationContext(), "Not fetching data, City not found", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Volley Error", Toast.LENGTH_LONG).show();
                    }
                }


        );
        requestQueue.add(jsonObjectRequest);

    }
}
