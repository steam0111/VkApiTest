package com.example.vkapitest.model.interactors;

import com.example.vkapitest.entity.VkFriend;
import com.example.vkapitest.model.repositories.FriendsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.realm.OrderedRealmCollection;

public class FriendsInteractor {

    private FriendsRepository mFriendsRepository;

    @Inject
    FriendsInteractor(FriendsRepository friendsRepository) {
        mFriendsRepository = friendsRepository;
    }

    public OrderedRealmCollection<VkFriend> getFriends() {
        int MAX_FRIENDS_IN_REQUEST = 100;
        mFriendsRepository
                .getVkFriendsListFromNetwork(MAX_FRIENDS_IN_REQUEST)
                .doOnSuccess(new Consumer<List<VkFriend>>() {
                    @Override
                    public void accept(List<VkFriend> vkFriends) {
                        mFriendsRepository.saveVkFriendsToDB(vkFriends);
                    }
                })
                .subscribe();

        return mFriendsRepository.getFriendsListFromDb();
    }

}
