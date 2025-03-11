package itu.jca.eval.test.coworking.utils;

import java.util.Arrays;

public class ImportUtils {
    public static String[] splitLine(String line,String separator,int cols) throws Exception{
        String[] values = line.split(separator);
        System.out.println("VALUES LENGTHs: "+values.length);
        if (values.length != cols) {
            throw new Exception("Format incorrecte de donnee doit etre avec "+cols+" valeurs");
        }
        System.out.println("VALUES SPLITTED: "+Arrays.toString(values));
        return values;
    }
}
