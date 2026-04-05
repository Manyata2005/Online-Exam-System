public class QuickSortTime {
 // Function to perform Quick Sort
 public static void quickSort(int[] arr, int low, int high) {
 if (low < high) {
 int p = partition(arr, low, high);
 quickSort(arr, low, p - 1);
 quickSort(arr, p + 1, high);
 }
 }
 // Partition function
 public static int partition(int[] arr, int low, int high) {
 int pivot = arr[high];
 int i = low - 1;
 for (int j = low; j < high; j++) {
 if (arr[j] < pivot) {
 i++;
 int temp = arr[i];
 arr[i] = arr[j];
 arr[j] = temp;
 }
 }
 int temp = arr[i + 1];
 arr[i + 1] = arr[high];
 arr[high] = temp;
 return i + 1;
 }
 // Generate random input
 public static int[] generateArray(int n) {
 int[] arr = new int[n];
 for (int i = 0; i < n; i++)
 arr[i] = (int) (Math.random() * n);
 return arr;
 }
 public static void main(String[] args) {
 int[] sizes = {6000, 8000, 10000};
 System.out.println("Size\tExecution Time (ns)");
 for (int n : sizes) {
 int[] arr = generateArray(n);
 long start = System.nanoTime();
 quickSort(arr, 0, arr.length - 1);
 long end = System.nanoTime();
 System.out.println(n + "\t" + (end - start));
 }
 }
}