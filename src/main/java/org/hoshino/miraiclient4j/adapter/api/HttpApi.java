package org.hoshino.miraiclient4j.adapter.api;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.GroupMessage;
import org.hoshino.miraiclient4j.message.messageRequest.TempMessage;
import org.hoshino.miraiclient4j.utils.R;

import java.io.File;

public abstract class HttpApi implements CommonApi{
    public abstract R<JSONObject> getSessionInfo();
    public abstract R<JSONObject> releaseSession(Long qq);
    public abstract R<JSONObject> countMessage();
    // 按时间顺序获取消息获取 count指获取消息的条数
    public abstract R<JSONObject> fetchMessage(int count);
    // 获取最新的消息
    public abstract R<JSONObject> fetchLatestMessage(int count);
    // 按时间顺序获取消息获取 但是不从队列中删除
    public abstract R<JSONObject> peekMessage(int count);
    public abstract R<JSONObject> peekLatestMessage(int count);

    /**
     * 上传图片文件到服务器 post请求 参数格式multipart/form-data
     * @param type 可选"friend"、"group"或"temp"
     * @param img 图片文件
     * @return
     */
    public abstract R<JSONObject> uploadImage(String type, File img);

    /**
     *上传音频文件到服务器 post请求 参数格式multipart/form-data
     * @param type 目前仅支持"group"
     * @param voice 声音文件
     * @return
     */
    public abstract R<JSONObject> uploadVoice(String type, File voice);

    /**
     * 上传群文件 post请求 参数格式multipart/form-data
     * @param type 当前仅支持"group"
     * @param target 上传目标群号
     * @param path 上传目录 空串上传到根目录
     * @param file 上传的文件
     * @return
     */
    public abstract R<JSONObject> fileUpload(String type, Long target, String path, File file);


}
