package io.fmsoftware.jumpyfun;

import io.fmsoftware.jumpyfun.KrisKross;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class KrisKrossTest
{
    private static KrisKross jumpyLib = new KrisKross();
    String json1 = "{ \"action\": \"run\", \"time\": 75 }";
    String json2 = "{ \"action\": \"swim\", \"time\": 732 }";
    String json3 = "{ \"action\": \"jump\", \"time\": 121 }";
    String json4 = "{ \"action\": \"run\", \"time\": 200 }";
    String json5 = "{ \"action\": \"ski\", \"time\": 543 }";
    String json6 = "{ \"action\": \"jump\", \"time\": 200 }";
    String json7 = "{ \"action\": \"run\", \"time\": 888 }";
    String json8 = "{ \"action\": \"jump\", \"time\": 389 }";
    String json9 = "{ \"action\": \"jump\", \"time\": 12 }";
    String json10 = "{ \"action\": \"run\", \"time\": 75 }";

    // negative values
    String json11 = "{ \"action\": \"\", \"time\": 100 }";
    String json12 = "{ \"action\": , \"time\":  }";
    String json13 = "{ \"action\": null, \"time\": null }";
    String json14 = "{ \"action\": \"jump\", \"time\": 0 }";
    String json15 = "{ \"action\": \"jump\", \"time\": -8 }";
    String json16 = "{ \"action\": \"jump\", \"time\":  }";
    String json17 = "{ \"action\": \"jump\", \"time\": null }";

    /**
     * Here we slay the dragons. Well, maybe spot them and then run for help.
     */
    @Test
    public void addActionTest()
    {
        try {
            jumpyLib.addAction(json1);
            jumpyLib.addAction(json2);
            jumpyLib.addAction(json3);
            jumpyLib.addAction(json4);
            jumpyLib.addAction(json5);
            jumpyLib.addAction(json6);
            jumpyLib.addAction(json7);
            jumpyLib.addAction(json8);
            jumpyLib.addAction(json9);
            jumpyLib.addAction(json10);
            assertTrue(true);
        }
        catch (Exception ex)
        {
            assertEquals("", ex.toString());
        }
    }

    @Test
    public void getStatsTest() {
        try {
            jumpyLib.addAction(json1);
            jumpyLib.addAction(json2);
            jumpyLib.addAction(json3);
            jumpyLib.addAction(json4);
            jumpyLib.addAction(json5);
            jumpyLib.addAction(json6);
            jumpyLib.addAction(json7);
            jumpyLib.addAction(json8);
            jumpyLib.addAction(json9);
            jumpyLib.addAction(json10);
            String answer = jumpyLib.getStats();
            assertEquals("[{\"action\":\"ski\",\"avg\":543},{\"action\":\"run\",\"avg\":309},{\"action\":\"swim\",\"avg\":732},{\"action\":\"jump\",\"avg\":180}]", answer);
        }
        catch (Exception ex) {
            assertEquals("", ex.toString());
        }
    }

    @Test
    public void resetStats() {
        try {
            jumpyLib.addAction(json1);
            jumpyLib.addAction(json2);
            jumpyLib.addAction(json3);
            jumpyLib.resetStats();
            String answer = jumpyLib.getStats();
            assertEquals("[]",answer);
        }
        catch (Exception ex)
        {
            assertEquals("", ex.toString());
        }
    }

    @Test
    public void emptyActionName() {
        try {
            jumpyLib.addAction(json11);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": \"\", \"time\": 100 }",ex.toString());
        }
    }

    @Test
    public void blankActionName() {
        try {
            jumpyLib.addAction(json12);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": , \"time\":  }",ex.toString());
        }
    }

    @Test
    public void nullActionNameTimeValue() {
        try {
            jumpyLib.addAction(json13);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": null, \"time\": null }",ex.toString());
        }
    }

    @Test
    public void zeroTimeValue() {
        try {
            jumpyLib.addAction(json14);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": \"jump\", \"time\": 0 }",ex.toString());
        }
    }

    @Test
    public void negativeTimeValue() {
        try {
            jumpyLib.addAction(json15);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": \"jump\", \"time\": -8 }",ex.toString());
        }
    }

    @Test
    public void emptyTimeValue() {
        try {
            jumpyLib.addAction(json16);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": \"jump\", \"time\":  }",ex.toString());
        }
    }

    @Test
    public void nullTimeValue() {
        try {
            jumpyLib.addAction(json17);
        }
        catch (Exception ex) {
            assertEquals("java.lang.Exception: There is an error with the json data: { \"action\": \"jump\", \"time\": null }",ex.toString());
        }
    }

}
