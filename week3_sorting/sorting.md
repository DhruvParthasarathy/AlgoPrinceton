# Sorting complexity

## Complexity of sorting

Computational complexity is the framework to study effeciency of algorithms for solving a particular problem X.

It contains
- the cost model ( the allowable operations ) 
- the upper bound ( the cost guarantee provided by some algorithm for X ) 
- the lower bound ( the best posible limit on the cost guarantee for all algoritms of X , no algorithm can do better than the lower bound )
- Optimal algorithm ( the algorithm which the best possible cost guarantee , which has both the upper bound and the lower bound as equal )

## Quick sort

1. We first randomly shuffle the array of comparable items
2. We assign the first item as lo and the last item as hi
3. We take the first item (lo) as the one which needs to be first placed in the correct sorted position
4. Starting i from lo and j from hi, we keep incrementing i till we find any element greater than lo. We keep decrementing j till we find some element lesser than lo.
5. Once we have stopped moving i and j, we swap it
6. We continue doing this swap till j crosses over i
7. Move the first item to the j'th index. Now we have 2 arrays, to either side of the j'th index element. 
8. Carry out the above steps for those sub arrays


Quicksort is not a stable sort as it moves keys over otehr keys.

## Selection

Selection problems usually involve some indication as to which element of the array has to be selected. Eg: Given an array of N items, find the kth smallest item. 

Once we are done with the shuffling and the partitioning, we can get to know if this item is towards the rigth or towards the left of the partitioning element. 

Thereby by just by looking at one side of the array,  alo of time is saved


## Duplicates

In the basic implementation of quick sort, we have just the one element as the partitioning element. But to handle duplicates, we use a 3 way partition method.

In the 3 way partition method, we have a block of elements in between which are equal to the partition element. We have one additional index which marks the end of this block of duplicate elements. 

lo, lp, hp, hi
lower bound, partition lower, partition higher, higher bound

### The 3 - way quick sort is much simpler and easy to understand