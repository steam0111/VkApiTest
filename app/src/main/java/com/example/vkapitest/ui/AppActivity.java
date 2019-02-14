package com.example.vkapitest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.vkapitest.R;
import com.example.vkapitest.entity.VkFriend;
import com.example.vkapitest.presentation.AppPresenter;
import com.example.vkapitest.presentation.AppView;
import com.example.vkapitest.toothpick.module.AppModule;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;

import org.jetbrains.annotations.NotNull;

import io.realm.OrderedRealmCollection;
import toothpick.Toothpick;

public class AppActivity extends MvpAppCompatActivity implements AppView {

    @InjectPresenter
    AppPresenter mPresenter;

    @ProvidePresenter
    AppPresenter providePresenter() {
        return Toothpick.openScope(AppModule.APP_SCOPE)
                .getInstance(AppPresenter.class);
    }

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        mRecyclerView = findViewById(R.id.rv_friends);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != 0) {
            if (!VK.onActivityResult(requestCode, resultCode, data, new VKAuthCallback() {
                @Override
                public void onLoginFailed(int i) {
                }

                @Override
                public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                    mPresenter.onUserLogined();
                }
            })) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onShowVkLoginActivity() {
        VK.login(this);
    }

    @Override
    public void onShowVkFriendsList(OrderedRealmCollection<VkFriend> data) {
        mRecyclerView.setAdapter(new FriendsRVAdapter(data));
    }
}
