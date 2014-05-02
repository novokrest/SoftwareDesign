import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/2/14.
 */
public class Luster extends LightingObject{
    private List<LightBulb> lightBulbs;

    public Luster(int number, int lightBulbsCount) {
        super(number);
        lightBulbs = new ArrayList<LightBulb>(lightBulbsCount);
        for (int i = 0; i < lightBulbsCount; i++) {
            lightBulbs.add(new LightBulb(i));
        }
    }

    @Override
    public void turnOn() throws LightingObjectBrokenException {
        int brokenLightBulbsCount = 0;
        for (LightBulb lightBulb : lightBulbs) {
            try {
                lightBulb.turnOn();
            } catch (Exception ex) {
                brokenLightBulbsCount++;
            }
        }
        if (brokenLightBulbsCount == lightBulbs.size()) {
            isBroken = true;
            throw new LightingObjectBrokenException("Luster_" + getNumber());
        }
    }

    @Override
    public void getStatus(String indent) {
        System.out.println(indent + "Luster_" + getNumber());
        for (LightBulb lightBulb : lightBulbs) {
            lightBulb.getStatus(indent + "\t");
        }
        System.out.println(indent + "Luster_" + getNumber() + " is " + (isBroken ? "off" : "on"));
    }
}
