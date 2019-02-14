package com.example.vkapitest.toothpick.provider;


import javax.inject.Inject;
import javax.inject.Provider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmProvider implements Provider<Realm> {

    @Inject
    RealmProvider() {}

    @Override
    public Realm get() {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

        return Realm.getDefaultInstance();
    }
}
