package com.arctouch.movies.movie.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.movies.BuildConfig;
import com.arctouch.movies.R;
import com.arctouch.movies.base.view.BaseActivity;
import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.model.MovieDetails;
import com.arctouch.movies.movie.presenter.MovieDetailsActivityPresenter;
import com.arctouch.movies.movie.presenter.MovieDetailsPresenter;
import com.arctouch.movies.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieDetailsActivity extends BaseActivity<MovieDetailsPresenter> implements MovieDetailsView {

    private TextView txtDesc;
    private TextView txtYear;
    private TextView txtRate;
    private TextView txtGenres;
    private ImageView imgToolBar;
    private ImageView imgPhoto;
    private Button btnWebSite;
    private Button btnShare;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private boolean isFavorite = false;

    @NonNull
    @Override
    protected MovieDetailsPresenter createPresenter(@NonNull Context context) {
        Movie movie = (Movie) getIntent().getSerializableExtra(Movie.EXTRA);
        return MovieDetailsActivityPresenter.newInstance(this, this, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        txtDesc = (TextView) findViewById(R.id.movie_details_text_desc);
        txtYear = (TextView) findViewById(R.id.movie_details_text_year);
        txtRate = (TextView) findViewById(R.id.movie_details_text_rate);
        txtGenres = (TextView) findViewById(R.id.movie_details_text_genres);

        imgToolBar = (ImageView) findViewById(R.id.movie_details_image_toolbar);
        imgPhoto = (ImageView) findViewById(R.id.movie_details_image_photo);

        btnWebSite = (Button) findViewById(R.id.movie_details_btn_website);
        btnShare = (Button) findViewById(R.id.movie_details_btn_share);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.movie_details_collapsing_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_details, menu);
        if (isFavorite) {
            MenuItem menuFav = menu.findItem(R.id.movie_details_menu_fav);
            menuFav.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
        }
        return true;
    }

    @Override
    public void showData(@NonNull MovieDetails movie) {
        txtGenres.setText(StringUtils.getMovieGenres(movie.getGenres()));
        btnWebSite.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(BuildConfig.BASE_APP_SITE + movie.getImdbId()));
            startActivity(intent);
        });

        btnShare.setOnClickListener(view -> {
            String url = BuildConfig.BASE_APP_SITE + movie.getImdbId();
            String message = "#HiperMovies #GoHiper " + url;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(intent, movie.getTitle()));
        });
    }

    @Override
    protected void configureToolbar(@NonNull ActionBar actionBar) {
        super.configureToolbar(actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.movie_details_menu_fav:
                isFavorite = !isFavorite;
                menuItem.setIcon(isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
                presenter.saveFavorite(isFavorite);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void setFavoriteMovie() {
        isFavorite = true;
        invalidateOptionsMenu();
    }

    @Override
    public void showPreData(@NonNull Movie movie) {
        getSupportActionBar().setTitle(movie.getTitle());
        int year = movie.getMovieYear();

        txtDesc.setText(movie.getOverview());
        txtYear.setText(Integer.toString(year));
        txtRate.setText(Float.toString(movie.getVoteAverage()));

        Glide.with(this)
                .load(BuildConfig.BASE_IMAGE_URL + "/w500" + movie.getPosterPath())
                .fitCenter()
                .into(new GlideDrawableImageViewTarget(imgPhoto) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        imgPhoto.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    }
                });

        Glide.with(this)
                .load(BuildConfig.BASE_IMAGE_URL + "/w500" + movie.getBackdropPath())
                .fitCenter()
                .into(new GlideDrawableImageViewTarget(imgToolBar) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        imgPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                });
    }

    @Override
    public void showFetchError() {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setMessage(R.string.movie_details_dialog_fetch_data_error)
                .setPositiveButton(R.string.dialog_ok, (dialog, which) -> {
                    finish();
                })
                .show();
    }
}
