package org.hoshino.miraiclient4j.adapter.api;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.utils.R;

public interface WebSocketApi {
    public R<JSONObject> connect(String verifyKey, String sessionKey, Long qq);
}
