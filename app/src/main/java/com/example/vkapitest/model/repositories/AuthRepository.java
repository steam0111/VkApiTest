package com.example.vkapitest.model.repositories;

import com.vk.api.sdk.VK;

import javax.inject.Inject;

public class AuthRepository {

    @Inject AuthRepository() {}

    public boolean isLoggedIn() {
        return VK.isLoggedIn();
    }
}
