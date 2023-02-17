package org.hoshino.miraiclient4j;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.baseType.Plain;
import org.hoshino.miraiclient4j.message.baseType.Source;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.Message;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes = MiraiClient4jApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MyTest {
    @Resource
    private Bot bot;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private HttpApiClient client;

//    @Test
//    public void test() throws JSONException {
//        JSONObject params = new JSONObject();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
//        headers.setContentType(mediaType);
//        headers.set("Accept",MediaType.APPLICATION_JSON.toString());
//        params.put("verifyKey", bot.getVerifyKey());
//        HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
//        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(MiraiURL.VERIFY_URL, entity, String.class);
//        System.out.println(stringResponseEntity.toString());
//    }

    @Test
    public void test1(){
        HttpApiClient client = new HttpApiClient();
        R<cn.hutool.json.JSONObject> sessionInfo = client.getSessionInfo();
        System.out.println(sessionInfo.getData().toString());
    }
    @Test
    public void test2(){
        String str = "{a:{b:c}, d:e}";
        JSONObject obj = JSONUtil.parseObj(str);

        System.out.println(obj.getByPath("a.b"));
    }

    @Test
    public void test3(){
        HttpApiClient client = new HttpApiClient();
        R<JSONObject> message = client.friendList();
        System.out.println(message.getData());
    }

    @Test
    public void test4(){
        String post = HttpUtil.post("http://localhost:8081/messageListener", "{\"code\": 400}");
        System.out.println(post);
    }

    @Test
    public void test5(){
        Source source = new Source(132, 46498);
        System.out.println(JSONUtil.parseObj(source));
    }
//    @Test
//    public void test6(){
//        Plain plain = new Plain("hello");
//
//        MessageEvent friendMessage = MessageBuilder.buildFriendMessage(473457308L,
//                new MessageChain().append(plain));
//
//        R<JSONObject> r = client.sendFriendMessage(friendMessage);
//        System.out.println(r);
//    }

    @Test
    public void test7(){
        Message msg = MessageBuilder.buildFriendMessage(473457308, new MessageChain().append(new Plain("321")));
        String s = JSONUtil.toJsonStr(msg);
        System.out.println(s);
    }

    @Test
    public void test8(){
        JSONObject entries = new JSONObject();
        entries.set("sessionKey", "sessionKey");
        entries.set("target", 13216446L);
        entries.set("messageChain", new MessageChain().append(new Plain("awdaw")));
        FriendMessage friendMessage = JSONUtil.toBean(JSONUtil.toJsonStr(entries), FriendMessage.class);
        System.out.println(JSONUtil.toJsonStr(friendMessage));
    }

    @Test
    public void test9(){
        String s = "{\"type\":\"FriendMessage\",\"messageChain\":[{\"type\":\"Source\",\"id\":23328,\"time\":1676188126},{\"type\":\"Plain\",\"text\":\"/echo hello\"}],\"sender\":{\"id\":473457308,\"nickname\":\"川下南署委员长\",\"remark\":\"川下南署委员长\"}}";
//        MessageChain teas = new MessageChain().append(new Plain("teas"));
        MessageEvent messageEvent = JSONUtil.toBean(s, MessageEvent.class);
        System.out.println(JSONUtil.toJsonStr(messageEvent));
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Test
    public void test10(){
        String s = "{\"verifyKey\":\"1234567890\"}";
        String post = HttpUtil.post(MiraiURL.VERIFY, JSONUtil.toJsonStr(JSONUtil.parseObj(s)));
        System.out.println(post);
    }
}
