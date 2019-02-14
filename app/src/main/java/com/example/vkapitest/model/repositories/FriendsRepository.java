package com.example.vkapitest.model.repositories;

import com.example.vkapitest.entity.VkFriend;
import com.example.vkapitest.entity.VkFriendsRequest;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmList;

public class FriendsRepository {

    @Inject Realm mRealm;

    @Inject
    FriendsRepository() {}

    public Single<List<VkFriend>> getVkFriendsListFromNetwork(final int maxCount) {

        return Single.create(new SingleOnSubscribe<List<VkFriend>>() {
            @Override
            public void subscribe(SingleEmitter<List<VkFriend>> emitter) throws Exception {

                VKRequest request = new VkFriendsRequest();

                request.addParam("count", maxCount);
                request.addParam("order", "hints");
                request.addParam("fields", "name");

                try {
                    emitter.onSuccess((List<VkFriend>)VK.executeSync(request));
                } catch (Throwable throwable) {
                    emitter.onError(throwable);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveVkFriendsToDB(final List<VkFriend> vkFriends) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(@NotNull Realm realmTransaction) {
                    RealmList<VkFriend> _vkFriendList = new RealmList<>();
                    _vkFriendList.addAll(vkFriends);
                    realmTransaction.insertOrUpdate(_vkFriendList);
                }
            });
    }

    public OrderedRealmCollection<VkFriend> getFriendsListFromDb(){
        return mRealm.where(VkFriend.class).findAll();
    }
}
