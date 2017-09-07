package com.arctouch.movies.home.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arctouch.movies.R;
import com.arctouch.movies.base.view.BaseFragment;
import com.arctouch.movies.home.view.adapter.HomeMoviesAdapter;
import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.view.MovieDetailsActivity;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MoviesFragment extends BaseFragment implements HomeMoviesAdapter.OnMovieItemClickListener {

    @Retention(SOURCE)
    @IntDef({TYPE_MOVIES, TYPE_FAVORITES})
    public @interface FragmentType {
    }

    public static final int TYPE_MOVIES = 0x01;
    public static final int TYPE_FAVORITES = 0x02;

    public static final String EXTRA_TYPE = "extra_type";

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private HomeMoviesAdapter moviesAdapter;

    private OnLoadMoreListener loadMoreListener;

    private boolean isLoadingMore = false;

    @FragmentType
    private int type;

    public static MoviesFragment newInstance(Bundle args, @Nullable OnLoadMoreListener listener) {
        MoviesFragment fragment = new MoviesFragment();
        fragment.setLoadMoreListener(listener);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movies_fragment, container, false);
        this.type = getArguments().getInt(EXTRA_TYPE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext());
        moviesAdapter = new HomeMoviesAdapter(new ArrayList<>(), this);

        recyclerView = (RecyclerView) view.findViewById(R.id.home_movie_recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                if (!isLoadingMore && totalItemCount <= (lastVisible + 5)
                        && loadMoreListener != null) {
                    isLoadingMore = true;
                    loadMoreListener.onLoadMore();
                    Log.i(getClass().getSimpleName(), "----> Load More!");

                }
            }
        });
    }

    public void setLoadMoreListener(@Nullable OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void hideLoadMoreLoading() {
        isLoadingMore = false;
    }

    @FragmentType
    public int getType() {
        return type;
    }

    public void showMovies(@NonNull List<Movie> movieList) {
        moviesAdapter.updateItems(movieList, isLoadingMore);
        isLoadingMore = false;
    }

    @Override
    public void onItemClicked(@NonNull Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(Movie.EXTRA, movie);
        startActivity(intent);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}
