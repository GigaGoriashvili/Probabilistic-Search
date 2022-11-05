public class ProbabilisticSearch{
    public static int[] callBinary (int[] a, int x) {
        return binarySearch(a, x, 0, a.length-1, 1);
    }

    public static int[] binarySearch (int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1+n2)/2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return binarySearch (a,x,t+1,n2, numberOfSteps + 1);
        else if (n1 < t)
            return binarySearch (a,x, n1, t-1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }

    public static int[] probalisticSearch(int[] arr, int value) {
        int ProbCounts = 0;
        int lowerBound = 0;
        int upperBound = 0;
        int[] array1;
        int position = (int) Math.round((double)(value - arr[0]) / (double)((double)(arr[arr.length - 1] - arr[0]) / (double)(arr.length - 1)));
        
        if (value == arr[position]) {
            return new int[]{position, 1};
        }
        
        if (arr[position] > value) {
            while (arr[position] > value) {
                upperBound = position;
                if (position - Math.pow(2, ProbCounts) < 0) {
                    ProbCounts++;
                    array1 = binarySearch(arr, value, 0, upperBound, ProbCounts + 1);
                    if(array1[0] == -1){
                        array1[1]--;
                    }
                    return new int[]{array1[0], array1[1]};
                }

                position -= Math.pow(2, ProbCounts);
                if (arr[position] == value) {
                    ProbCounts++;
                    return new int[]{position, ProbCounts + 1};
                }
                ProbCounts++;
            }

             lowerBound = position;
                array1 = binarySearch(arr, value, lowerBound, upperBound, ProbCounts + 1);
                if(array1[0] == -1){
                    array1[1]--;
                }
                return new int[]{array1[0], array1[1]};
        }

        if (arr[position] < value) {
            while (arr[position] < value) {
                lowerBound=position;
                if (position + Math.pow(2, ProbCounts) >= arr.length) {
                    ProbCounts++;
                    array1 = binarySearch(arr, value, lowerBound, arr.length - 1, ProbCounts + 1);
                    if(array1[0] == -1){
                        array1[1]--;
                    }
                    return new int[]{array1[0], array1[1]};
                }
                
                position += Math.pow(2, ProbCounts);
                if (arr[position] == value) {
                    ProbCounts++;
                    return new int[]{position, ProbCounts + 1};
                }
                ProbCounts++;
            }

            upperBound = position;
            array1 = binarySearch(arr, value, lowerBound, upperBound, ProbCounts + 1);
            if(array1[0] == -1){
                array1[1]--;
              }
            return new int[]{array1[0], array1[1]};
        }
         return new int[]{-1, ProbCounts + 1};
    }
    
    //This code compares two algorithmes' performance and shows how fast the probabilistic search is
    public static void compareApproaches(int[] arr, int min, int max) {
        int[] binResult = callBinary(arr, min);
        int[] probResult = probalisticSearch(arr, min);
        long sumOfCallsBin = binResult[1];;
        int maxCallsBin = binResult[1];
        int numWithMaxCallsBin = min;
        long sumOfCallsProb = probResult[1];;
        int maxCallsProb = probResult[1];
        int numWithMaxCallsProb = min;
        for (int i = min + 1; i <= max; i++) {
            binResult = callBinary(arr, i);
            sumOfCallsBin += binResult[1];
            if (binResult[1] > maxCallsBin) {
                maxCallsBin = binResult[1];
                numWithMaxCallsBin = i;
            }
            probResult = probalisticSearch(arr, i);
            sumOfCallsProb += probResult[1];
            if (probResult[1] > maxCallsProb) {
                maxCallsProb = probResult[1];
                numWithMaxCallsProb = i;
            }
        }
        System.out.println("Binary Search:");
        System.out.println("Maximum number of calls:");
        System.out.println(maxCallsBin);
        System.out.println("Value at which the maximum number of calls occurs:");
        System.out.println(numWithMaxCallsBin);
        System.out.println("Number of total calls:");
        System.out.println(sumOfCallsBin);

        System.out.println("Probabilistic Search:");
        System.out.println("Maximum number of calls:");
        System.out.println(maxCallsProb);
        System.out.println("Value at which the maximum number of calls occurs:");
        System.out.println(numWithMaxCallsProb);
        System.out.println("Number of total calls:");
        System.out.println(sumOfCallsProb);
    }

    public static void main(String[] args) {
        int[] exampleArray = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
        compareApproaches(exampleArray, exampleArray[0], exampleArray[exampleArray.length - 1]);
    }
}