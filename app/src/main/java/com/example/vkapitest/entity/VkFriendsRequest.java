package com.example.vkapitest.entity;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VkFriendsRequest extends VKRequest<List<VkFriend>> {

    public VkFriendsRequest() {
        super("friends.get");
    }

    @Override
    public List<VkFriend> parse(@NotNull JSONObject r) throws Exception {
        JSONArray vkFriendsJsonArray = r.getJSONObject("response").getJSONArray("items");

        ArrayList<VkFriend> vkFriends = new ArrayList<VkFriend>();

        if (vkFriendsJsonArray != null) {
            for (int i=0; i < vkFriendsJsonArray.length(); i++){
                vkFriends.add(new VkFriend(
                        vkFriendsJsonArray.getJSONObject(i).getLong("id"),
                        vkFriendsJsonArray.getJSONObject(i).getString("first_name")
                ));
            }
        }

        return vkFriends;
    }
}
