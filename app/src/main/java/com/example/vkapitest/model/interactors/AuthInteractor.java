package com.example.vkapitest.model.interactors;

import com.example.vkapitest.model.repositories.AuthRepository;

import javax.inject.Inject;

public class AuthInteractor {

    private AuthRepository mAuthRepository;

    @Inject
    AuthInteractor(AuthRepository authRepository) {
        mAuthRepository = authRepository;
    }

    public boolean isSignIn() {
        return mAuthRepository.isLoggedIn();
    }
}
