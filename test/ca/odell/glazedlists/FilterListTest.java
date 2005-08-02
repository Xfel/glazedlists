/* Glazed Lists                                                 (c) 2003-2005 */
/* http://publicobject.com/glazedlists/                      publicobject.com,*/
/*                                                     O'Dell Engineering Ltd.*/
package ca.odell.glazedlists;

import junit.framework.TestCase;
import ca.odell.glazedlists.matchers.*;
import java.util.*;

/**
 * Tests the generic FilterList class.
 */
public class FilterListTest extends TestCase {

    /**
     * This test demonstrates Issue 213.
     */
    public void testRelax() {
        // construct a (contrived) list of initial values
        BasicEventList original = new BasicEventList();
        List values = intArrayToIntegerCollection(new int [] { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 0, 10, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 1 });
        original.addAll(values);
        
        // prepare a filter to filter our list
        MinimumValueMatcherEditor editor = new MinimumValueMatcherEditor();
        FilterList myFilterList = new FilterList(original, editor);
        myFilterList.addListEventListener(new ConsistencyTestList(myFilterList, "filter"));
        
        // relax the list
        editor.setMinimum(11);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(10);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(0);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(10);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(11);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(0);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        
        // now try constrain
        values = intArrayToIntegerCollection(new int[] { 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11 });
        original.clear();
        original.addAll(values);
        
        // constrain the list
        editor.setMinimum(10);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(11);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(12);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(10);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));

        // now try more changes
        values = intArrayToIntegerCollection(new int[] { 8, 6, 7, 5, 3, 0, 9 });
        original.clear();
        original.addAll(values);

        // constrain the list
        editor.setMinimum(5);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(10);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(1);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(0);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));
        editor.setMinimum(1);
        assertEquals(myFilterList, filter(original, editor.getMatcher()));

    }

	/**
	 * Test Matchers that fire matchAll() and matchNone() events.
	 */
	public void testMatchAllOrNothing() {
		EventList base_list = new BasicEventList();
		base_list.add(new Integer(1));
		base_list.add(new Integer(2));
		base_list.add(new Integer(3));
		base_list.add(new Integer(4));
		base_list.add(new Integer(5));

		AllOrNothingMatcherEditor matcher = new AllOrNothingMatcherEditor();
		FilterList filter_list = new FilterList(base_list,matcher);
		filter_list.addListEventListener(new ConsistencyTestList(filter_list, "Filter List"));

		// Test initial size
		assertEquals(5, filter_list.size());

		// Clear it
		matcher.showAll(false);
		assertEquals(0, filter_list.size());

		// Put it back
		matcher.showAll(true);
		assertEquals(5, filter_list.size());
	}

	/**
     * Convert the specified int[] array to a List of Integers.
     */
    private List intArrayToIntegerCollection(int[] values) {
        List result = new ArrayList();
        for(int i = 0; i < values.length; i++) {
            result.add(new Integer(values[i]));
        }
        return result;
    }
    
    /**
     * Manually apply the specified filter to the specified list.
     */
    private List filter(List input, Matcher matcher) {
        List result = new ArrayList();
        for(Iterator i = input.iterator(); i.hasNext(); ) {
            Object element = i.next();
            if(matcher.matches(element)) result.add(element);
        }
        return result;
    }
    
    /**
     * A MatcherEditor for minimum values.
     */
    static class MinimumValueMatcherEditor extends AbstractMatcherEditor {
        private int minimum = 0;
        public MinimumValueMatcherEditor() {
            minimum = 0;
            currentMatcher = new MinimumValueMatcher(0);
        }
        public void setMinimum(int value) {
            if(value < minimum) {
                this.minimum = value;
                fireRelaxed(new MinimumValueMatcher(minimum));
            } else if(value == minimum) {
                // do nothing
            } else {
                this.minimum = value;
                fireConstrained(new MinimumValueMatcher(minimum));
            }
        }
    }
    
    /**
     * This matcher matches everything greater than its minimum.
     */
    static class MinimumValueMatcher implements Matcher {
        private int minimum;
        public MinimumValueMatcher(int minimum) {
            this.minimum = minimum;
        }
        public boolean matches(Object value) {
            return ((Integer)value).intValue() >= minimum;
        }
    }

	/**
	 * Matcher that allows testing matchAll() and matchNone().
	 */
	static class AllOrNothingMatcherEditor extends AbstractMatcherEditor {
		private boolean show_all = true;		// otherwise nothin'


		/**
		 * @param state True show everything, otherwise show nothing
		 */
		public void showAll(boolean state) {
			if (state == show_all) return;

			show_all = state;
			if (state) fireMatchAll();
			else fireMatchNone();
		}

		public Matcher getMatcher() {
			return show_all ? Matchers.trueMatcher() : Matchers.falseMatcher();
		}
	}
}