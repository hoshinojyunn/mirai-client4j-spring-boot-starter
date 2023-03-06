package io.github.hoshinojyunn.miraiclient4j.message.baseType;

import cn.hutool.json.JSONObject;

import java.util.List;

public class ForwardMessage extends AbstractType implements BaseType {
    private List<JSONObject> nodeList;

    public ForwardMessage(List<JSONObject> nodeList) {
        super.setType("Forward");
        this.nodeList = nodeList;
    }

    public List<JSONObject> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<JSONObject> nodeList) {
        this.nodeList = nodeList;
    }


}
