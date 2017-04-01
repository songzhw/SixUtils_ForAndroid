package ca.six.demo;

import ca.six.common.IMerge;
import ca.six.common.Router3;

public class AppRouteInfo implements IMerge {
    @Override
    public void merge() {
        Router3.getInstance().merge("login", LoginActivity.class);
        Router3.getInstance().merge("questions", QuestionsActivity.class);
        Router3.getInstance().merge("third", ThirdActivity.class);
    }
}
