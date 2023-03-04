package org.hoshino.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.utils.R;

public interface SendStrategy {
    R<JSONObject> doStrategy();
}
