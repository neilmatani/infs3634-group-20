package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";

    public static final String API_KEY = "dd7eb4843b4241f588f4db05924abda6";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;

    private String TAG = ReadingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Intent intent = getIntent();
        String title = intent.getStringExtra(INTENT_MESSAGE);

        setTitle(title + " Readings");

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(ReadingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        //function called as per selected Module
        if(title.equals("Politics")) {
            loadPoliticsJson();
        }else if(title.equals("Australia")){
            loadAusJson();
        }else if(title.equals("Pandemic")){
            loadPandemicJson();
        }
    }

    //call using politics queries
    public void loadPoliticsJson(){

        NewsInterface newsInterface = NewsApiClient.getNewsApiClient().create(NewsInterface.class);
        //String country = Utils.getCountry();
        Call<News> call;
        call = newsInterface.getNews("government AND election", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new NewsAdapter(articles, ReadingActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "No Result!", Toast.LENGTH_SHORT).show();

                Log.w("ReadingActivity.java", t.getMessage());
            }
        });
    }

    //call using Australia query
    public void loadAusJson(){

        NewsInterface newsInterface = NewsApiClient.getNewsApiClient().create(NewsInterface.class);
        //String country = Utils.getCountry();
        Call<News> call;
        call = newsInterface.getNews("australia", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new NewsAdapter(articles, ReadingActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "No Result!", Toast.LENGTH_SHORT).show();

                Log.w("ReadingActivity.java", t.getMessage());
            }
        });
    }


    //call using Pandemic query
    public void loadPandemicJson(){

        NewsInterface newsInterface = NewsApiClient.getNewsApiClient().create(NewsInterface.class);
        //String country = Utils.getCountry();
        Call<News> call;
        call = newsInterface.getNews("COVID-19 AND coronavirus", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new NewsAdapter(articles, ReadingActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "No Result!", Toast.LENGTH_SHORT).show();

                Log.w("ReadingActivity.java", t.getMessage());
            }
        });
    }
}