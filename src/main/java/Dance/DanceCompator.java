package Dance;

import java.util.Comparator;

public class DanceCompator implements Comparator<Dance> {
    @Override
    public int compare(Dance o1, Dance o2) {
        return o1.getDanceID().compareTo(o2.getDanceID());
    }
}
