import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/2/14.
 */
public class ComplexLightSystem extends LightingObject {
    private List<ILightingObject> lightingObjects;

    public ComplexLightSystem(int number) {
        super(number);
        lightingObjects = new ArrayList<ILightingObject>();
    }

    public void addLightingObject(ILightingObject lightingObject) {
        lightingObjects.add(lightingObject);
    }

    @Override
    public void turnOn() throws LightingObjectBrokenException {
        int brokenLightingObjectCount = 0;
        for (ILightingObject lightingObject : lightingObjects) {
            try {
                lightingObject.turnOn();
            } catch (LightingObjectBrokenException ex) {
                brokenLightingObjectCount++;
            }
        }
        if (brokenLightingObjectCount == lightingObjects.size()) {
            throw new LightingObjectBrokenException("ComplexLightSystem_" + getNumber());
        }
    }

    @Override
    public void getStatus(String indent) {
        System.out.println(indent + "ComplexLightSystem_" + getNumber());
        for (ILightingObject lightingObject : lightingObjects) {
            lightingObject.getStatus(indent + "\t");
        }
        System.out.println(indent + "ComplexLightSystem_" + getNumber() + " is " + (isBroken ? "off" : "on"));
    }
}
