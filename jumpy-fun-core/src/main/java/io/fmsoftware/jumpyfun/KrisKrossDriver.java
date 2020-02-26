package io.fmsoftware.jumpyfun;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrisKrossDriver
{
    private static final Logger logger = LogManager.getLogger(KrisKrossDriver.class);

    public static void main( String[] args )
    {
        logger.debug("Inside driver main");
        KrisKross jumpyLib = new KrisKross();
        String json = "{ \"action\": \"jump\", \"time\": 100 }";
        try {
            logger.debug("Starting driver");
            logger.debug("Json passed in: "+json);


            jumpyLib.addAction(json);
        }
        catch (Exception ex) {
            logger.error("{}", ex);
        }
        finally {
            logger.debug("Driver Done.\n");
        }
    }
}
