package entryTest;

/**
 * Created by lr on 20.10.2016.
 */
public class LargestFinder {
    public int[] getLargest(int[] array, int k) {
        int elementsToFound = k;
        if (isWrongArguments(array, elementsToFound))
            return new int[0];

        if (array.length <= elementsToFound) {
            int largestElements[] = new int[array.length];
            System.arraycopy(array, 0, largestElements, 0, array.length);

            return largestElements;
        }

        int largestElements[] = new int[elementsToFound];

        for (int i=0; i<array.length; i++) {
            int foundElements = i < elementsToFound ? i : elementsToFound;

            int elementToCheck = array[i];
            int index = binarySearch(largestElements, 0, foundElements, elementToCheck);
            int positionToPaste = index >= 0 ? index : -index - 1;
            if (positionToPaste >= elementsToFound) {
                continue;
            }

            if (positionToPaste < foundElements) {
                int elementsToMove = foundElements - positionToPaste;
                if (positionToPaste + elementsToMove == elementsToFound) {
                    elementsToMove--;
                }

                if (elementsToMove > 0)
                    System.arraycopy(largestElements, positionToPaste, largestElements, positionToPaste + 1, elementsToMove);
            }

            largestElements[positionToPaste] = elementToCheck;
        }
        return largestElements;
    }

    private boolean isWrongArguments(int[] array, int k) {
        if (array == null || array.length == 0) {
            return true;
        }
        if (k <= 0) {
            return true;
        }
        return false;
    }

    private int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                high = mid - 1;
            else if (midVal > key)
                low = mid + 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
