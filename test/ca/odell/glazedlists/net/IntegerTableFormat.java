/**
 * Glazed Lists
 * http://glazedlists.dev.java.net/
 *
 * COPYRIGHT 2003 O'DELL ENGINEERING LTD.
 */
package ca.odell.glazedlists.net;

// glazed lists
import ca.odell.glazedlists.*;
import ca.odell.glazedlists.swing.*;

/**
 * The IntegerTableFormat specifies how an integer is displayed in a table.
 * 
 * @author <a href="mailto:jesse@odel.on.ca">Jesse Wilson</a>
 */
public class IntegerTableFormat implements TableFormat {
    
    public int getColumnCount() {
        return 1;
    }

    public String getColumnName(int column) {
        return "Integer";
    }

    public Object getColumnValue(Object baseObject, int column) {
        return baseObject;
    }
}