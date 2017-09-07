package com.arctouch.movies.home.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arctouch.movies.R;
import com.arctouch.movies.home.view.fragment.MoviesFragment;
import com.arctouch.movies.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieFragmentAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Fragment> fragmentTabs;

    public MovieFragmentAdapter(@NonNull FragmentManager fm, Context context,
                                @NonNull MoviesFragment.OnLoadMoreListener loadMoreListener) {
        super(fm);
        this.context = context;
        this.buildFragments(loadMoreListener);
    }

    private void buildFragments(@NonNull MoviesFragment.OnLoadMoreListener loadMoreListener) {
        fragmentTabs = new ArrayList<>();

        Bundle bTypeMovies = new Bundle();
        bTypeMovies.putInt(MoviesFragment.EXTRA_TYPE, MoviesFragment.TYPE_MOVIES);
        MoviesFragment moviesFragment = MoviesFragment.newInstance(bTypeMovies, loadMoreListener);
        fragmentTabs.add(moviesFragment);

        Bundle bTypeFavs = new Bundle();
        bTypeFavs.putInt(MoviesFragment.EXTRA_TYPE, MoviesFragment.TYPE_FAVORITES);
        MoviesFragment moviesFavsFragment = MoviesFragment.newInstance(bTypeFavs, null);
        fragmentTabs.add(moviesFavsFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentTabs.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Bundle bundle = fragmentTabs.get(position).getArguments();
        int type = bundle.getInt(MoviesFragment.EXTRA_TYPE);

        String title = "";
        switch (type) {
            case MoviesFragment.TYPE_MOVIES:
                title = context.getString(R.string.home_title_movies);
                break;
            case MoviesFragment.TYPE_FAVORITES:
                title = context.getString(R.string.home_title_favorites);
                break;
        }

        return title;
    }

    @MoviesFragment.FragmentType
    public int getPageType(int position) {
        return ((MoviesFragment) fragmentTabs.get(position)).getType();
    }

    public void showMovies(@MoviesFragment.FragmentType int type, @NonNull List<Movie> movieList) {
        Observable.fromIterable(fragmentTabs)
                .filter(fragment -> ((MoviesFragment) fragment).getType() == type)
                .subscribe(fragment -> ((MoviesFragment) fragment).showMovies(movieList));
    }

    public void hideLoadMoreLoading() {
        Observable.fromIterable(fragmentTabs).forEach(fragment -> ((MoviesFragment) fragment).hideLoadMoreLoading());
    }

}
