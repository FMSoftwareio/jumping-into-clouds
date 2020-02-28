package io.fmsoftware.jumpydemo;

import io.fmsoftware.jumpyfun.KrisKross;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadedKrisKross extends Thread {
    private static final Logger logger = LogManager.getLogger(ThreadedKrisKross.class);
    private String jsonMsg;
    private KrisKross jumpyLib;
    private int threadNum;

    ThreadedKrisKross(String jsonMsg, KrisKross jumpyLib, int threadNum) {
        this.jsonMsg = jsonMsg;
        this.jumpyLib = jumpyLib;
        this.threadNum = threadNum;
    }

    public void run() {
        try {
            jumpyLib.addAction(jsonMsg);
            System.out.println("ThreadNum: "+threadNum+" "+jumpyLib.getStats());
        } catch (Exception ex) {
            logger.fatal("{}", ex);
        }
    }
}
