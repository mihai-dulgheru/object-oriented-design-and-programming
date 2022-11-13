package ro.ase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    public static final String FORMAT_DATA = "dd.MM.yyyy";

    public static Date convertesteData(String data) {
        try {
            return new SimpleDateFormat(FORMAT_DATA).parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertesteData(Date data) {
        return new SimpleDateFormat(FORMAT_DATA).format(data);
    }

    public static String convertesteData(Date data, String format) {
        return new SimpleDateFormat(format).format(data);
    }
}
