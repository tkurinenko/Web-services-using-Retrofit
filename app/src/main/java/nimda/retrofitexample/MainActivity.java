package nimda.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import nimda.retrofitexample.POJO.Model;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    TextView city, status, humidity, pressure, temperature;
    String url = "http://api.openweathermap.org/data/2.5";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (TextView) findViewById(R.id.txt_city);
        status = (TextView) findViewById(R.id.txt_status);
        humidity = (TextView) findViewById(R.id.txt_humidity);
        pressure = (TextView) findViewById(R.id.txt_press);
        temperature = (TextView) findViewById(R.id.txt_temp);


        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

        //create Rest Services

        RestInterface restInterface = adapter.create(RestInterface.class);

        //Calling method to get whether report
        restInterface.getWheatherReport(new Callback<Model>() {
            @Override
            public void success(Model model, Response response) {
                city.setText("city :"+model.getName());
                status.setText("Status :"+model.getWeather().get(0).getDescription());
                humidity.setText("humidity :"+model.getMain().getHumidity().toString());
                pressure.setText("pressure :"+model.getMain().getPressure().toString());

                double kelvin_temp = model.getMain().getTemp();
                double celsium = kelvin_temp - 273.0;
                temperature.setText("temperature : "+ celsium);
            }

            @Override
            public void failure(RetrofitError error) {
                String mError = error.getMessage();
            }
        });
    }

}
