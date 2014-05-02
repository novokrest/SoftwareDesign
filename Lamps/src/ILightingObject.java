/**
 * Created by Admin on 5/2/14.
 */
public interface ILightingObject {
    public int getNumber();
    public boolean isBroken();
    public void turnOn() throws LightingObjectBrokenException;
    public void getStatus(String indent);
}
