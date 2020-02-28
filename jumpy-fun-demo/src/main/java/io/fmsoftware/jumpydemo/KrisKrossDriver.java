package io.fmsoftware.jumpydemo;

import io.fmsoftware.jumpyfun.KrisKross;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KrisKrossDriver
{
    private static final Logger logger = LogManager.getLogger(KrisKrossDriver.class);
    private static KrisKross jumpyLib = new KrisKross();

    public static void main( String[] args )
    {
        KrisKrossDriver kkd = new KrisKrossDriver();
        kkd.threadDemo();
        jumpyLib.resetStats();
        kkd.basicDemo();
    }

    public void threadDemo() {
        logger.debug("Inside thread demo");

        ThreadedKrisKross t1 = new ThreadedKrisKross("{ \"action\": \"jump\", \"time\": 100 }", jumpyLib, 1);
        ThreadedKrisKross t2 = new ThreadedKrisKross("{ \"action\": \"run\", \"time\": 75 }", jumpyLib, 2);
        ThreadedKrisKross t3 = new ThreadedKrisKross("{ \"action\": \"swim\", \"time\": 732 }", jumpyLib, 3);
        ThreadedKrisKross t4 = new ThreadedKrisKross("{ \"action\": \"jump\", \"time\": 121 }", jumpyLib, 4);
        ThreadedKrisKross t5 = new ThreadedKrisKross("{ \"action\": \"jump\", \"time\": 200 }", jumpyLib, 5);
        ThreadedKrisKross t6 = new ThreadedKrisKross("{ \"action\": \"swim\", \"time\": 543 }", jumpyLib, 6);
        ThreadedKrisKross t7 = new ThreadedKrisKross("{ \"action\": \"jump\", \"time\": 200 }", jumpyLib, 7);
        ThreadedKrisKross t8 = new ThreadedKrisKross("{ \"action\": \"ski\", \"time\": 888 }", jumpyLib, 8);
        ThreadedKrisKross t9 = new ThreadedKrisKross("{ \"action\": \"run\", \"time\": 389 }", jumpyLib, 9);
        ThreadedKrisKross t10 = new ThreadedKrisKross("{ \"action\": \"run\", \"time\": 12 }", jumpyLib, 10);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        try
        {
            //wait on threads
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();

            System.out.println("Thread demo final stats: "+jumpyLib.getStats());
        }
        catch(Exception ex)
        {
            logger.fatal("{}", ex);
            System.out.println("We got some bad juju...");
        }
        finally {
            System.out.println("Thread demo done.\n");
        }
    }

    public void basicDemo() {
        logger.debug("Inside driver main");
        KrisKross jumpyLib = new KrisKross();
        String json = "{ \"action\": \"jump\", \"time\": 100 }";
        String json1 = "{ \"action\": \"run\", \"time\": 75 }";
        String json2 = "{ \"action\": \"jump\", \"time\": 200 }";

        try {
            logger.debug("Starting driver");
            System.out.println("Basic test start(empty):"+jumpyLib.getStats());

            logger.debug("Json passed in: "+json);
            jumpyLib.addAction(json);

            logger.debug("Json passed in: "+json1);
            jumpyLib.addAction(json1);

            logger.debug("Json passed in: "+json2);
            jumpyLib.addAction(json2);

            String stats = jumpyLib.getStats();
            logger.debug("Basic test final stats: "+stats);
            System.out.println("Basic test final stats: "+stats);
        }
        catch (Exception ex) {
            logger.error("{}", ex);
        }
        finally {
            logger.debug("Driver Done.\n");
        }
    }
}
