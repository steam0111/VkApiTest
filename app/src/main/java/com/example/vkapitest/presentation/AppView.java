package com.example.vkapitest.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vkapitest.entity.VkFriend;

import io.realm.OrderedRealmCollection;

public interface AppView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void onShowVkLoginActivity();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onShowVkFriendsList(OrderedRealmCollection<VkFriend> data);
}
