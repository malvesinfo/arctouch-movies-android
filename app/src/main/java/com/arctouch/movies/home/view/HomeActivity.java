package com.arctouch.movies.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arctouch.movies.R;
import com.arctouch.movies.base.view.BaseActivity;
import com.arctouch.movies.home.presenter.HomeActivityPresenter;
import com.arctouch.movies.home.presenter.HomePresenter;
import com.arctouch.movies.home.view.adapter.MovieFragmentAdapter;
import com.arctouch.movies.home.view.fragment.MoviesFragment;
import com.arctouch.movies.movie.model.Movie;
import com.lapism.searchview.SearchView;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView, MoviesFragment.OnLoadMoreListener {

    private SearchView searchView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MovieFragmentAdapter fragmentAdapter;
    private MenuItem searchMenuItem;

    private boolean hideSearchMenu = false;

    @NonNull
    @Override
    protected HomePresenter createPresenter(@NonNull Context context) {
        return HomeActivityPresenter.newInstance(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        tabLayout = (TabLayout) findViewById(R.id.home_tab);
        viewPager = (ViewPager) findViewById(R.id.home_pager);

        fragmentAdapter = new MovieFragmentAdapter(
                getSupportFragmentManager(),
                this, this);

        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                @MoviesFragment.FragmentType
                int type = fragmentAdapter.getPageType(position);
                hideSearchMenu = type != MoviesFragment.TYPE_MOVIES;
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        searchView = (SearchView) findViewById(R.id.home_search_view);
        searchView.setArrowOnly(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    presenter.startSearch(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void configureToolbar(@NonNull ActionBar actionBar) {
        super.configureToolbar(actionBar);
        actionBar.setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        searchMenuItem = menu.findItem(R.id.home_menu_search);
        searchMenuItem.setVisible(!hideSearchMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.home_menu_search) {
            searchView.open(!searchView.isSearchOpen());
            return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void showMovies(List<Movie> movieList) {
        fragmentAdapter.showMovies(MoviesFragment.TYPE_MOVIES, movieList);
    }

    @Override
    public void showFavoriteMovies(List<Movie> movieList) {
        fragmentAdapter.showMovies(MoviesFragment.TYPE_FAVORITES, movieList);
    }

    @Override
    public void showFetchError() {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setMessage(R.string.home_dialog_fetch_data_error)
                .setPositiveButton(R.string.dialog_ok, (dialog, which) -> {

                })
                .show();
    }

    @Override
    public void onLoadMore() {
        presenter.loadMore(searchView.getQuery().toString());
    }

    @Override
    public void showHasNoMoreData() {
        Snackbar.make(findViewById(R.id.coordinator_layout),
                R.string.home_load_more_limit, Snackbar.LENGTH_LONG);
        fragmentAdapter.hideLoadMoreLoading();
    }
}
