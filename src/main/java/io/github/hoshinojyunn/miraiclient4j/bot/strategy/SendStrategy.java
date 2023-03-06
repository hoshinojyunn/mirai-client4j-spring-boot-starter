package io.github.hoshinojyunn.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import io.github.hoshinojyunn.miraiclient4j.utils.R;

public interface SendStrategy {
    R<JSONObject> doStrategy();
}
