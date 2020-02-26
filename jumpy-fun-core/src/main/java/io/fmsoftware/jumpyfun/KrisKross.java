package io.fmsoftware.jumpyfun;

import java.util.HashMap;
import com.google.gson.*;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrisKross
{
    private static final Logger logger = LogManager.getLogger(KrisKross.class);
    private HashMap<String, Integer> stats = new HashMap<>();

    public void addAction(String json) throws Exception {
        logger.debug("Inside: addAction");
        JsonObject jsonObj = new Gson().fromJson(json, JsonObject.class);

//        Assert.assertTrue(jsonObj.isJsonObject());
//        Assert.assertEquals("jump", jsonObj.get("action").getAsString());
//        Assert.assertEquals(jsonObj.get("time").getAsInt(), 100);

        logger.debug("addAction Done.");
    }

    public String getStats() throws Exception {
        return "stub";
    }


}