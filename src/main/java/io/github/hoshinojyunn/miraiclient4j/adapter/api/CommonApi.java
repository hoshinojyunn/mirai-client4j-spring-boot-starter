package io.github.hoshinojyunn.miraiclient4j.adapter.api;

import cn.hutool.json.JSONObject;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.request.*;
import org.hoshino.miraiclient4j.message.messageRequest.request.*;
import io.github.hoshinojyunn.miraiclient4j.utils.R;

public interface CommonApi {
    /**
     * /about [GET]
     * @return
     */
    default R<JSONObject> about(){
        return null;
    }

    /**
     * /botList [GET]
     * @return
     */
    default R<JSONObject> botList(){
        return null;
    }

    /**
     * /messageFromId [GET]
     * @param messageId
     * @param target
     * @return
     */
    default R<JSONObject> messageFromId(int messageId, Long target){
        return null;
    }

    /**
     * /friendList
     * @return
     */
    default R<JSONObject> friendList(){
        return null;
    }

    /**
     * /groupList [GET]
     * @return
     */
    default R<JSONObject> groupList(){
        return null;
    }

    /**
     * /memberList 或者 /latestMemberList [GET]
     * 获取群成员列表
     * @param target 群号
     * @return
     */
    default R<JSONObject> memberList(Long target){
        return null;
    }

    /**
     * /botProfile [GET]
     * @return
     */
    default R<JSONObject> botProfile(){
        return null;
    }

    /**
     * /friendProfile [GET]
     * @param target
     * @return
     */
    default R<JSONObject> friendProfile(Long target){
        return null;
    }

    /**
     * /memberProfile [GET]
     * @param target 群号
     * @param memberId 成员qq号
     * @return
     */
    default R<JSONObject> memberProfile(Long target, Long memberId){
        return null;
    }

    /**
     * /userProfile [GET]
     * @param target
     * @return
     */
    default R<JSONObject> userProfile(Long target){
        return null;
    }

    /**
     * /sendFriendMessage [POST]
     * @param message
     * @return
     */
    default R<JSONObject> sendFriendMessage(FriendMessage message){
        return null;
    }

    /**
     * /sendGroupMessage [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> sendGroupMessage(GroupMessage groupMessage){
        return null;
    }

    /**
     * /sendTempMessage [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> sendTempMessage(TempMessage groupMessage){
        return null;
    }

    /**
     * /sendNudge [POST]
     * @param nudgeMessage
     * @return
     */
    default R<JSONObject> sendNudge(NudgeMessage nudgeMessage){
        return null;
    }

    /**
     * /recall [POST]
     * @param recallMessage
     * @return
     */
    default R<JSONObject> recall(RecallMessage recallMessage){
        return null;
    }

    /**
     * /roamingMessages [POST]
     * @param roamingMessage
     * @return
     */
    default R<JSONObject> roamingMessages(RoamingMessage roamingMessage){
        return null;
    }

    /**
     * /file/list [GET]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> fileList(GroupMessage groupMessage){
        return null;
    }

    /**
     * /file/info [GET]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> fileInfo(GroupMessage groupMessage){
        return null;
    }

    /**
     * /file/delete [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> fileDelete(GroupMessage groupMessage){
        return null;
    }

    /**
     * /file/move [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> fileMove(GroupMessage groupMessage){
        return null;
    }

    /**
     * /file/rename [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> fileRename(GroupMessage groupMessage){
        return null;
    }

    /**
     * /deleteFriend [POST]
     * @param groupMessage
     * @return
     */
    default R<JSONObject> deleteFriend(GroupMessage groupMessage){
        return null;
    }
}
