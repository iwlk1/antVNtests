package opengltests2;

/**
 * Created by ian on 6/5/2014.
 */
public class img_ops {

    private static int convertARGBtoRGBA(int input){

        return Integer.rotateLeft(input, 8);

    }

}
