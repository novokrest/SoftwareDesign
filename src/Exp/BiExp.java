package Exp;

/**
 * Created by novokrest on 4/16/14.
 */
public abstract class BiExp implements Exp {
    public final Exp left;
    public final Exp right;

    protected BiExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }
}
