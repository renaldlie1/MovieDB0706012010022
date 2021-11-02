package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.ProCompAdapter;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_overview,lbl_title,lbl_release_date,lbl_movie_avg_vote,lbl_movie_popular,lbl_tagline,lbl_movie_genres_details;
    private ImageView image_poster,image_backdrop;
    private ProCompAdapter proCompAdapter;
    private Movies movies;
    RecyclerView recyclerView ;

    public List<Movies.ProductionCompanies> getProductionCompanies(int id){
        movies = new Movies();
        List<Movies.ProductionCompanies> list = new ArrayList<>();
        if(movies.getId() == id){
            list = movies.getProduction_companies();
        }
        return list;
    }

    public List<Movies.Genres> getGenres(int id){
        movies = new Movies();
        List<Movies.Genres> genresList = new ArrayList<>();
        if(movies.getId() == id ){
            genresList = movies.getGenres();
        }
        return genresList;
    }

    private androidx.lifecycle.Observer<Movies> movieObs = new androidx.lifecycle.Observer<Movies> (){

        @Override
        public void onChanged(Movies movies) {
            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            ProCompAdapter adapter1 = new ProCompAdapter(getActivity());
            adapter1.setMovieProComp(movies.getProduction_companies());
            recyclerView.setAdapter(adapter1);

            Toast.makeText(getContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
            lbl_tagline.setText(movies.getTagline());
            String genre = "";
            for (int i=0 ; i<movies.getGenres().size() ; i++){
                if(i == movies.getGenres().size()-1){
                    genre += movies.getGenres().get(i).getName();
                }else{
                    genre += movies.getGenres().get(i).getName()+" ,";
                }
            }
            lbl_movie_genres_details.setText(genre);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        recyclerView = view.findViewById(R.id.rv_procomp);
        lbl_movie_genres_details = view.findViewById(R.id.lbl_movie_genres_details);
        String movieId = getArguments().getString("movieId");
        proCompAdapter = new ProCompAdapter(getActivity());
        proCompAdapter.setMovieProComp(getProductionCompanies(Integer.valueOf(movieId)));
        recyclerView.setAdapter(proCompAdapter);
        MovieViewModel movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        movieViewModel.getMovieById(movieId);
        movieViewModel.getResultGetMovieById().observe(getActivity(),movieObs);

        lbl_overview = view.findViewById(R.id.lbl_movie_overview_details);
        String movieOverview = getArguments().getString("movieOverview");
        lbl_overview.setText(movieOverview);
        lbl_title = view.findViewById(R.id.lbl_movie_title_details);
        String movieTitle = getArguments().getString("movieTitle");
        lbl_title.setText(movieTitle);
        lbl_release_date = view.findViewById(R.id.lbl_movie_release_date);
        String movieRelease = getArguments().getString("movieRelease");
        lbl_release_date.setText(movieRelease);
        lbl_movie_avg_vote = view.findViewById(R.id.lbl_movie_avg_vote);
        String movieAvg = getArguments().getString("movieAvg");
        lbl_movie_avg_vote.setText(movieAvg);
        lbl_movie_popular = view.findViewById(R.id.lbl_movie_popular);
        String moviePop = getArguments().getString("moviePop");
        lbl_movie_popular.setText(moviePop);
        lbl_tagline = view.findViewById(R.id.lbl_tagline);
        String movieTag = getArguments().getString("movieTag");
        lbl_tagline.setText(movieTag);
        image_poster =  (ImageView) view.findViewById(R.id.image_poster);
        String moviePoster = getArguments().getString("moviePoster");
        String img_path = moviePoster;
        String full_path = "https://image.tmdb.org/t/p/w500/" + img_path;
        Glide.with(MovieDetailsFragment.this).load(full_path).into(image_poster);
        image_backdrop = (ImageView) view.findViewById(R.id.image_backdrop);
        String movieBackdrop = getArguments().getString("movieBackdrop");
        String img_backdrop = movieBackdrop;
        String full_pathBack = "https://image.tmdb.org/t/p/w500/" + img_backdrop;
        Glide.with(MovieDetailsFragment.this).load(full_pathBack).into(image_backdrop);

        return view;

    }
}