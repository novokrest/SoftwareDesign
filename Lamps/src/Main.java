/**
 * Created by Admin on 5/2/14.
 */
public class Main {
    public static void main(String[] args) {
        ComplexLightSystem complexLightSystem1 = new ComplexLightSystem(1);
        ComplexLightSystem complexLightSystem2 = new ComplexLightSystem(2);
        LightBulb lightBulb1 = new LightBulb(1);
        LightBulb lightBulb2 = new LightBulb(2);
        LightBulb lightBulb3 = new LightBulb(3);
        Luster luster1 = new Luster(1, 5);
        Luster luster2 = new Luster(2, 2);
        Luster luster3 = new Luster(3, 10);
        Garland garland1 = new Garland(1, 5);
        Garland garland2 = new Garland(2, 3);

        complexLightSystem1.addLightingObject(lightBulb1);
        complexLightSystem1.addLightingObject(lightBulb3);
        complexLightSystem1.addLightingObject(garland1);
        complexLightSystem1.addLightingObject(luster1);
        complexLightSystem1.addLightingObject(luster2);

        complexLightSystem2.addLightingObject(lightBulb2);
        complexLightSystem2.addLightingObject(luster3);
        complexLightSystem2.addLightingObject(complexLightSystem1);
        complexLightSystem2.addLightingObject(garland2);

        for (int i = 0; i < 20; i++) {
            System.out.println("=== Attempt " + i + " ===");
            try {
                complexLightSystem2.turnOn();
            } catch (LightingObjectBrokenException ex) {

            } finally {
                complexLightSystem2.getStatus("");
            }
            System.out.println();
        }
    }
}
