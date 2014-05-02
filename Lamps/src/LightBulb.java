/**
 * Created by Admin on 5/2/14.
 */
public class LightBulb extends LightingObject{
    public LightBulb(int number) {
        super(number);
    }

    @Override
    public void turnOn() throws LightingObjectBrokenException {
        if (isBroken || Math.random() < 0.05) {
            isBroken = true;
            throw new LightingObjectBrokenException("LightBulb_" + getNumber());
        }
    }

    @Override
    public void getStatus(String indent) {
        System.out.println(indent + "LightBulb_" + getNumber() + " is " + (isBroken ? "off" : "on"));
    }
}
