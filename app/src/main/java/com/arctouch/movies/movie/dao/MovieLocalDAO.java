package com.arctouch.movies.movie.dao;

import com.arctouch.movies.db.AppDatabaseTransaction;
import com.arctouch.movies.movie.dao.entity.MovieEntity;
import com.arctouch.movies.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieLocalDAO extends AppDatabaseTransaction<Movie> {

    @Override
    public void save(Movie data) {
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(MovieEntity.toEntity(data)));
        realm.close();
    }

    @Override
    public void save(List<Movie> dataList) {
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(MovieEntity.toEntity(dataList)));
        realm.close();
    }

    @Override
    public void remove(Movie data) {
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm1 -> {
            MovieEntity entity = realm.where(MovieEntity.class).equalTo("id", data.getId()).findFirst();
            if (entity != null) entity.deleteFromRealm();
        });
        realm.close();
    }

    @Override
    public void update(Movie data) {
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(MovieEntity.toEntity(data)));
        realm.close();
    }

    @Override
    public void clear() {
        realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        realm.delete(MovieEntity.class);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public List<Movie> get() {
        realm = Realm.getInstance(realmConfig);
        RealmResults<MovieEntity> results = realm.where(MovieEntity.class).findAll();
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            movieList.add(results.get(i).toModel());
        }
        realm.close();
        return movieList;
    }

    public boolean hasMovie(long id) {
        boolean result = false;
        realm = Realm.getInstance(realmConfig);
        MovieEntity entity = realm.where(MovieEntity.class).equalTo("id", id).findFirst();
        if (entity != null) result = true;
        realm.close();
        return result;
    }
}
