package org.hoshino.miraiclient4j.adapter.api;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.utils.R;

public interface CommonApi {
    // 插件信息
    public R<JSONObject> about();
    // 当前登录所有账号
    public R<JSONObject> botList();
    public R<JSONObject> messageFromId(int messageId, Long target);
    //获取bot的好友列表
    public R<JSONObject> friendList();
    public R<JSONObject> groupList();
    // 获取群成员列表 target填群号
    public R<JSONObject> memberList(Long target);
    // 获取bot的详细资料
    public R<JSONObject> botProfile();
    public R<JSONObject> friendProfile(Long target);
    // target群号  memberId成员qq号
    public R<JSONObject> memberProfile(Long target, Long memberId);
    public R<JSONObject> sendFriendMessage(FriendMessage message);
    // TODO: 2023/2/8 未完成

}
