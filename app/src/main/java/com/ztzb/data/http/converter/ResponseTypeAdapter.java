package com.ztzb.data.http.converter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ztzb.data.http.response.BaseResponse;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 当出现data为null的时候会出现空指针，在这个地方处理
 */
public class ResponseTypeAdapter implements JsonDeserializer<BaseResponse> {
    @Override
    public BaseResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonObject()) {
            final JsonObject obj = json.getAsJsonObject();
            if (obj.get("data").isJsonNull()) {
                return new BaseResponse(new JSONObject(), obj.get("errorMsg").getAsString(), obj.get("errorCode").getAsInt());
            } else {
                return new Gson().fromJson(json, typeOfT);
            }
        }
        return null;
    }


}
