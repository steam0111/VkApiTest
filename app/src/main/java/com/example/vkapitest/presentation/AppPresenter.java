package com.example.vkapitest.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vkapitest.model.interactors.AuthInteractor;
import com.example.vkapitest.model.interactors.FriendsInteractor;

import javax.inject.Inject;

@InjectViewState
public class AppPresenter extends MvpPresenter<AppView> {

    @Inject
    AuthInteractor mAuthInteractor;

    @Inject
    FriendsInteractor mFriendsInteractor;

    @Override
    protected void onFirstViewAttach() {
        if (!mAuthInteractor.isSignIn()) {
            getViewState().onShowVkLoginActivity();
        } else {
            getFriends();
        }
    }

    public void onUserLogined() {
        getFriends();
    }

    private void getFriends() {
        getViewState().onShowVkFriendsList(mFriendsInteractor.getFriends());
    }
}
