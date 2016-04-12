package nimda.retrofitexample;

import nimda.retrofitexample.POJO.Model;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by tkurinenko on 12.04.2016.
 */
public interface RestInterface {

    @GET("/weather?q=Kyiv,uk&appid=" + Config.OPENWEATHERMAP_API_KEY)
    void getWheatherReport(Callback<Model> cb);
}
