package io.fmsoftware.jumpyfun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrisKross {
    private class KrisKrossAvgStats {
        String action;
        int avg;
    }

    private class KrisKrossActionStats {
        int totalTime;
        int count;
    }

    private static final Logger logger = LogManager.getLogger(KrisKross.class);
    private HashMap<String, KrisKrossActionStats> actions = new HashMap<>();
    private final String actionElementName = "action";
    private final String timeElementName = "time";

    public synchronized void addAction(String json) throws Exception {
        JsonObject jsonObj;
        String action = null;
        Integer time = null;
        logger.debug("Inside: addAction");
        try {
            jsonObj = new Gson().fromJson(json, JsonObject.class);
            action = parseActionElement(jsonObj);
            time = parseTimeElement(jsonObj);
        }
        catch (com.google.gson.JsonSyntaxException ex) {
            logger.fatal("{}", ex);
        }

        if ((action != null) && (time != null)) {
            boolean hasAction = actions.containsKey((action));

            if (hasAction) {
                updateAction(action, time);
            }
            else {
                addNewAction(action, time);
            }
        }
        else {
            logger.error("There is an error with the json data: "+json);
        }
        logger.debug("addAction Done.");
    }

    public synchronized String getStats() throws Exception {
        logger.debug("Inside: getStats");
        Gson gson = new Gson();
        ArrayList<KrisKrossAvgStats> avgs = new ArrayList<>();

        for (Map.Entry<String, KrisKrossActionStats> entry : actions.entrySet()) {
            String key = entry.getKey();
            KrisKrossActionStats value = entry.getValue();

            KrisKrossAvgStats jsonStats = new KrisKrossAvgStats();
            jsonStats.action = key;
            //NOTE: Integer division remainders will be truncated.
            jsonStats.avg = value.totalTime/value.count;
            avgs.add(jsonStats);
        }

        String json = gson.toJson(avgs);
        logger.debug(json);
        return json;
    }

    public synchronized void resetStats() {
        this.actions = new HashMap<>();
    }

    private String parseActionElement(JsonObject jsonObj) {
        JsonElement element;

        if (jsonObj.has(actionElementName)) {
            element = jsonObj.get(actionElementName);

            if (element.isJsonNull()) {
                logger.error("Action value cannot be null.");
            }
            else {
                //This will convert any action element that is a number into a string.
                return element.getAsString();
            }
        }
        return null;
    }

    private Integer parseTimeElement(JsonObject jsonObj) {
        JsonElement element;

        if (jsonObj.has(timeElementName)) {
            element = jsonObj.get(timeElementName);

            if (element.isJsonNull()) {
                logger.error("Time value cannot be null.");
            } else {
                //This will truncate any value that is not a whole number.
                try {
                    int value = element.getAsInt();
                    if (value > 0) {
                        return value;
                    }
                }
                catch (java.lang.NumberFormatException ex) {
                    logger.error("Time value must be an integer.", ex);
                }
            }
        }
        return null;
    }

    private synchronized void addNewAction(String action, Integer time) {
        KrisKrossActionStats item = new KrisKrossActionStats();
        item.count = 1;
        item.totalTime = time;

        actions.put(action, item);
    }

    private synchronized void updateAction(String action, Integer time) {
        //NOTE: This is a reference.
        KrisKrossActionStats item = actions.get(action);

        item.totalTime += time;
        item.count += 1;
    }
}