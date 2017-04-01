package ca.six.mod2;

import ca.six.common.IMerge;
import ca.six.common.Router3;

public class Mod2RouteInfo implements IMerge {
    @Override
    public void merge(){
        Router3.getInstance().merge("mod2", Module2Activity.class);
    }
}
