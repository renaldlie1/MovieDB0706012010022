package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.view.fragments.MovieDetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class ProCompAdapter extends RecyclerView.Adapter<ProCompAdapter.CardViewViewHolder>{
    private Context context;
    private List<Movies.ProductionCompanies> movieProComp = new ArrayList<>();


    private List<Movies.ProductionCompanies> getListProComp(){ return movieProComp; }
    public ProCompAdapter(Context context){ this.context = context; }

    public void setMovieProComp(List<Movies.ProductionCompanies> movieProComp) {
        this.movieProComp = movieProComp;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production_company, parent, false);
        return new ProCompAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies results = getListProComp().get(position);

        String img_procomp = movieProComp.get(position).getLogo_path();
        String full_path = "https://image.tmdb.org/t/p/w500/" + img_procomp;
        Glide.with(getContext()).load(full_path).into(holder.img_procomp);
        holder.img_procomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Toast.makeText(c,"Hello Javatpoint",Toast.LENGTH_SHORT);
            }
        });
        

    }

    @Override
    public int getItemCount() {
        return getListProComp().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_procomp;
        CardView cvPro;
        TextView lbl_title_procomp;


        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cvPro = itemView.findViewById(R.id.cv_procomp);
            img_procomp = (ImageView) itemView.findViewById(R.id.image_procomp);


        }



    }
    
}
