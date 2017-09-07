package com.arctouch.movies.home.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arctouch.movies.BuildConfig;
import com.arctouch.movies.R;
import com.arctouch.movies.movie.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class HomeMoviesAdapter extends RecyclerView.Adapter<HomeMoviesAdapter.ViewHolder> {

    private List<Movie> movieList;
    private OnMovieItemClickListener listener;

    public HomeMoviesAdapter(@NonNull List<Movie> movieList, @NonNull OnMovieItemClickListener listener) {
        this.movieList = movieList;
        this.listener = listener;

    }

    @Override
    public HomeMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeMoviesAdapter.ViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public void updateItems(@NonNull List<Movie> movieList, boolean loadingMore) {
        if (!this.movieList.isEmpty() && loadingMore) {
            this.movieList.addAll(movieList);
        } else {
            this.movieList = movieList;
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlMainLayout;

        private TextView txtName;
        private TextView txtRate;
        private TextView txtYear;
        private ImageView imgPhoto;
        private ImageView imgBackDrop;

        private Movie movie;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.movie_item_text_name);
            txtRate = (TextView) itemView.findViewById(R.id.movie_item_text_rate);
            txtYear = (TextView) itemView.findViewById(R.id.movie_item_text_year);
            imgPhoto = (ImageView) itemView.findViewById(R.id.movie_item_image_photo);
            imgBackDrop = (ImageView) itemView.findViewById(R.id.movie_item_image_backdrop);

            rlMainLayout = (RelativeLayout) itemView.findViewById(R.id.movie_item_main_layout);

            itemView.setOnClickListener(v -> listener.onItemClicked(movie));
        }

        public void bind(Movie movie) {
            this.movie = movie;
            int year = movie.getMovieYear();

            txtName.setText(movie.getTitle());
            txtRate.setText(Float.toString(movie.getVoteAverage()));
            txtYear.setText(Integer.toString(year));

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_IMAGE_URL + "/w500" + movie.getPosterPath())
                    .fitCenter()
                    .into(new GlideDrawableImageViewTarget(imgPhoto) {
                        @Override
                        public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                            super.onResourceReady(drawable, anim);
                            imgPhoto.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        }
                    });

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_IMAGE_URL + "/w500" + movie.getBackdropPath())
                    .fitCenter()
                    .into(new GlideDrawableImageViewTarget(imgBackDrop) {
                        @Override
                        public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                            super.onResourceReady(drawable, anim);
                            imgPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                    });
        }
    }

    public interface OnMovieItemClickListener {
        void onItemClicked(@NonNull Movie movie);
    }
}
