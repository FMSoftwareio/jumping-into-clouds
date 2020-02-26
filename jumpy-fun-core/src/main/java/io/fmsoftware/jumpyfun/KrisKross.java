package io.fmsoftware.jumpyfun;

import java.util.HashMap;
import com.google.gson.*;
import org.junit.Assert;

public class KrisKross
{
    private HashMap<String, Integer> stats = new HashMap<>();

    public void addAction(String json) throws Exception {
        JsonObject jsonObj = new Gson().fromJson(json, JsonObject.class);

        Assert.assertTrue(jsonObj.isJsonObject());
        Assert.assertEquals("jump", jsonObj.get("action").getAsString());
        Assert.assertEquals(jsonObj.get("time").getAsInt(), 100);
    }

    public String getStats() throws Exception {
        return "stub";
    }


}