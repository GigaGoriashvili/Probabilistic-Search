# Probabilistic-Search
# Faster-Search-for-Sorted-Arrays
We already know the binary search, which allows us to find an entry in a sorted array in logarithmic time. Now I want to try to use a different approach to achieve even better performance in many use cases - though not in general.

The algorithm that I implemented is Probabilistic Search. With the help of assumptions about the probability distribution of the possible values in the array (whereby we are assuming the simplest case), an attempt is made to determine a (hopefully favorable) starting position in the array and to search from there.

For the probabilistic search we need a sorted array, as well as the minimum and maximum value and the number of elements of the array.

This program is to show us how faster is Probabilistic Search than Binary one by testing both for the same arrays.