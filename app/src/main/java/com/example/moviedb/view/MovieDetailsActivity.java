package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_overview,lbl_title,lbl_release_date;
    private String movie_id,movie_overview,movie_title,movie_poster,movie_date,movie_rating = "";
    private ImageView img_poster;
    private RatingBar ratingBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        movie_overview = intent.getStringExtra("movie_overview");
        movie_title = intent.getStringExtra("movie_title");
        movie_poster = intent.getStringExtra("movie_poster");
        movie_date = intent.getStringExtra("movie_date");
        movie_rating = intent.getStringExtra("movie_rating");












        lbl_overview = findViewById(R.id.lbl_movie_overview_details);
        lbl_overview.setText(movie_overview);
        lbl_title = findViewById(R.id.lbl_movie_title_details);
        lbl_title.setText(movie_title);
        lbl_release_date = findViewById(R.id.lbl_movie_release_date);
        lbl_release_date.setText(movie_date);
        img_poster = (ImageView) findViewById(R.id.img_poster_nowplaying);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat(movie_rating)/2);




        //movie_rating = findViewById(R.id.movie_rating);

        String img_path = movie_poster;
        String full_path = "https://image.tmdb.org/t/p/w500/" + img_path;
        Glide.with(MovieDetailsActivity.this).load(full_path).into(img_poster);







    }
    @Override
    public void onBackPressed() {
        finish();
    }
}