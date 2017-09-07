package com.arctouch.movies.db;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class AppDatabaseTransaction<T> {

    protected Realm realm;
    protected RealmConfiguration realmConfig;

    public AppDatabaseTransaction() {
        realmConfig = new RealmConfiguration.Builder().build();
    }

    public void close() {
        if (realm != null) realm.close();
    }

    public void executeUpdateTransaction(final DatabaseTransaction transaction) {
        realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm -> transaction.execute());
    }

    public abstract void save(T data);

    public abstract void save(List<T> dataList);

    public abstract void remove(T data);

    public abstract void update(T data);

    public abstract void clear();

    public abstract List<T> get();

    public interface DatabaseTransaction {
        void execute();
    }
}
