package com.example.vkapitest.toothpick.module;

import android.content.Context;

import com.example.vkapitest.model.interactors.AuthInteractor;
import com.example.vkapitest.model.interactors.FriendsInteractor;
import com.example.vkapitest.model.repositories.AuthRepository;
import com.example.vkapitest.model.repositories.FriendsRepository;
import com.example.vkapitest.toothpick.provider.RealmProvider;

import io.realm.Realm;
import toothpick.config.Module;

public class AppModule extends Module {

    public static final String APP_SCOPE = "app scope";

    public AppModule(Context context) {
        bind(Context.class).toInstance(context);
        bind(Realm.class).toProvider(RealmProvider.class).providesSingletonInScope();

        bind(AuthInteractor.class);
        bind(AuthRepository.class);

        bind(FriendsInteractor.class);
        bind(FriendsRepository.class);
    }
}
