package com.example.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nowPlayingFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nowPlaying.
     */
    // TODO: Rename and change types and number of parameters
    public static nowPlayingFragment newInstance(String param1, String param2) {
        nowPlayingFragment fragment = new nowPlayingFragment();
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

    private RecyclerView rv_now_playing,rv_procomp;
    private MovieViewModel view_model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        rv_now_playing = view.findViewById(R.id.rv_now_playing_fragment);
        rv_procomp = view.findViewById(R.id.rv_procomp);
        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getNowPlaying();
        view_model.getResultNowPlaying().observe(getActivity(),showNowPlaying);

        return view;
    }

     private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {

            rv_now_playing.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);

            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("movieTitle", ""+nowPlaying.getResults().get(position).getTitle());
                bundle.putString("movieOverview" , ""+nowPlaying.getResults().get(position).getOverview());
                bundle.putString("movieRelease",""+nowPlaying.getResults().get(position).getRelease_date());
                bundle.putString("movieAvg",""+nowPlaying.getResults().get(position).getVote_average());
                bundle.putString("moviePop",""+nowPlaying.getResults().get(position).getPopularity());
                bundle.putString("moviePoster",""+nowPlaying.getResults().get(position).getPoster_path());
                bundle.putString("movieBackdrop",""+nowPlaying.getResults().get(position).getBackdrop_path());
                bundle.putString("movieGenre",""+nowPlaying.getResults().get(position));
                bundle.putString("movieId",""+nowPlaying.getResults().get(position).getId());

                Navigation.findNavController(v).navigate(R.id.action_nowPlaying_to_movieDetailFragment,bundle);

                }
            });

        }
    };

}