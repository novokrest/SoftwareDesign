/**
 * Created by Admin on 5/2/14.
 */
public abstract class LightingObject implements ILightingObject {
    private int number;
    protected boolean isBroken;

    public LightingObject(int number) {
        this.number = number;
        isBroken = false;
    }

    @Override
    public boolean isBroken() {
        return isBroken;
    }

    @Override
    public int getNumber() { return number; }
}
