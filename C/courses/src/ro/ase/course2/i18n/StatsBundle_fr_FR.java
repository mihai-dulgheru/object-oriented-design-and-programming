package ro.ase.course2.i18n;

import java.util.ListResourceBundle;

public class StatsBundle_fr_FR extends ListResourceBundle {

    private Object[][] contents = {
            {"GDP", new Integer(12300)},
            {"Population", new Integer(1234567)},
            {"Literacy", new Double(0.97)},
    };

    public Object[][] getContents() {
        return contents;
    }
}
