package ca.six.mod1;

import ca.six.common.IMerge;
import ca.six.common.Router3;

public class Mod1RouteInfo implements IMerge{
    @Override
    public void merge() {
        Router3.getInstance().merge("mod1", Module1Activity.class);
    }
}
