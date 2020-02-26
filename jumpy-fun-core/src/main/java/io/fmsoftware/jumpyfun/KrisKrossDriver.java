package io.fmsoftware.jumpyfun;


public class KrisKrossDriver
{
    public static void main( String[] args )
    {
        KrisKross driver = new KrisKross();
        String json = "{ \"action\": \"jump\", \"time\": 100 }";
        try {
            System.out.println("Starting driver");
            System.out.println("Json passed: "+json);

            driver.addAction(json);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Driver Done.");
        }
    }
}
