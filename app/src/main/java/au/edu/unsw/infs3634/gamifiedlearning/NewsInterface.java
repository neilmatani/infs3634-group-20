package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //HTTP GET calls for articles in JSON format
    @GET("top-headlines")
    Call<News> getHeadlines(
            @Query("country") String country ,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<News> getNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey

    );

}
