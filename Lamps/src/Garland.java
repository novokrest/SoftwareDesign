import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/2/14.
 */
public class Garland extends LightingObject{
    private List<LightBulb> lightBulbs;

    public Garland(int number, int lightBulbsCount) {
        super(number);
        lightBulbs = new ArrayList<LightBulb>(lightBulbsCount);
        for (int i = 0; i < lightBulbsCount; i++) {
            lightBulbs.add(new LightBulb(i));
        }
    }

    @Override
    public void turnOn() throws LightingObjectBrokenException {
        try {
            for (LightBulb lightBulb : lightBulbs) {
                lightBulb.turnOn();
            }
        } catch (LightingObjectBrokenException ex) {
            isBroken = true;
            throw new LightingObjectBrokenException("Garland_" + getNumber());
        }
    }

    @Override
    public void getStatus(String indent) {
        System.out.println(indent + "Garland_" + getNumber());
        for (LightBulb lightBulb : lightBulbs) {
            System.out.println(indent + "\t" +
                    "LightBulb_" + lightBulb.getNumber() + " is " + (isBroken ? "off" : "on"));
        }
        System.out.println(indent + "Garland_" + getNumber() + " is " + (isBroken ? "off" : "on"));
    }
}
