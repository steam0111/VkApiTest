package com.example.vkapitest;

import android.app.Application;

import com.example.vkapitest.toothpick.module.AppModule;
import com.vk.api.sdk.VK;

import io.realm.Realm;
import toothpick.Scope;
import toothpick.Toothpick;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        VK.initialize(this);
        Realm.init(this);
        initAppScope();
    }


    private void initAppScope() {
        Scope appScope = Toothpick.openScope(AppModule.APP_SCOPE);
        appScope.installModules(new AppModule(this));
    }
}
