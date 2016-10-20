package entryTest;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by lr on 20.10.2016.
 */
public class LargestFinderTest {
    LargestFinder largestFinder = new LargestFinder();

    @Test
    public void testNullArray() {
        int[] array = null;
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[0]));
    }

    @Test
    public void testEmptyArray() {
        int[] array = new int[0];
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[0]));
    }

    @Test
    public void testInvalidCountElementsToFind() {
        int[] array = {1, 2, 3, 4, 5};
        int countToFind = -1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[0]));
    }

    @Test
    public void testCountElementsToFindIncreaseArraySize() {
        int[] array = {1, 2, 3, 4, 5};
        int countToFind = array.length+2;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(array));
    }

    @Test
    public void testSmallArrayLookingElementsInTheBegin() {
        int[] array = {10, 9, 8, 8, 6};
        int countToFind = 2;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10, 9}));
    }

    @Test
    public void testVerySmallArray() {
        int[] array = {2, 10};
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10}));
    }

    @Test
    public void testSmallArrayLookingElementsInTheEnd() {
        int[] array = {2, 4, 6, 8, 10};
        int countToFind = 2;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10, 8}));
    }

    @Test
    public void testLookingForAlmostAllArray() {
        int[] array = {10, 9, 8, 8, 6};
        int countToFind = 4;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10, 9, 8, 8}));
    }

    @Test
    public void testLookingForAlmostAllArrayButArrayElementsAreEqual() {
        int[] array = {10, 10, 10, 10, 10};
        int countToFind = 4;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10, 10, 10, 10}));
    }

    @Test
    public void testLookingForOneElementButArrayElementsAreEqual() {
        int[] array = {10, 10, 10, 10, 10};
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10}));
    }

    @Test
    public void testLookingForOneElementItLastInArray() {
        int[] array = {0, 0, 0, 0, 10};
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10}));
    }

    @Test
    public void testLookingForOneElementItFirstInArray() {
        int[] array = {10, 0, 0, 0, 0};
        int countToFind = 1;

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(new int[] {10}));
    }

    @Test
    public void testLargeArray() {
        int arraySize = 10_000_000;
        int[] array = new int[arraySize];
        Random random = new Random();
        int maxValue = 10_000;
        int countToFind = 10_000;

        for (int i=0; i<arraySize; i++) {
            array[i] = random.nextInt(maxValue);
        }

        int needResult[] = new int[countToFind];

        int position = 74;
        for (int i = 0; i < countToFind; i++) {
            final int currentValue = maxValue * 10 + i;
            needResult[countToFind - i - 1] = currentValue;
            array[position] = currentValue;
            position += 33;
        }

        int[] largestElements = largestFinder.getLargest(array, countToFind);

        assertThat(largestElements, equalTo(needResult));
    }
}