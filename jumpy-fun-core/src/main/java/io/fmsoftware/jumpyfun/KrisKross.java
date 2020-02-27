package io.fmsoftware.jumpyfun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrisKross {
    private static class KrisKrossAvgStats {
        String action;
        int avg;
    }

    private static class KrisKrossActionStats {
        int totalTime;
        int count;
    }

    private static final Logger logger = LogManager.getLogger(KrisKross.class);
    private HashMap<String, KrisKrossActionStats> actions = new HashMap<>();
    private final String actionElementName = "action";
    private final String timeElementName = "time";

    public void addAction(String json) throws Exception {
        logger.debug("Inside: addAction");
        JsonObject jsonObj = new Gson().fromJson(json, JsonObject.class);
        String action = parseActionElement(jsonObj);
        Integer time = parseTimeElement(jsonObj);

        if ((action != null) && (time != null)) {
            // Needs semaphore
            boolean hasAction = actions.containsKey((action));

            if (hasAction) {
                updateAction(action, time);
            }
            else {
                addNewAction(action, time);
            }
        }
        logger.debug("addAction Done.");
    }

    public String getStats() throws Exception {
        logger.debug("Inside: getStats");
        Gson gson = new Gson();
        // Needs semaphore
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

        logger.debug(gson.toJson(avgs));
        return gson.toJson(avgs);
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
                    return element.getAsInt();
                }
                catch (java.lang.NumberFormatException ex) {
                    logger.error("Time value must be an integer.", ex);
                }
            }
        }
        return null;
    }

    private void addNewAction(String action, Integer time) {
        KrisKrossActionStats item = new KrisKrossActionStats();
        item.count = 1;
        item.totalTime = time;

        // Needs a semaphore
        actions.put(action,item);
    }

    private void updateAction(String action, Integer time) {
        // Needs a semaphore
        KrisKrossActionStats item = actions.get(action);

        item.totalTime += time;
        item.count += 1;
    }
}