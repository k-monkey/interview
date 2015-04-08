# Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

# For example,
# Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. 
# see leetcode.com

class Solution:
    # @param A, a list of integers
    # @return an integer
    def trap(self, A):
        if not A:
            return 0
            
        biggest = -1
        biggest_from_left = []
        for i in A:
            biggest_from_left.append(biggest)
            biggest = max(i, biggest)
        
        biggest = -1
        biggest_from_right = []
        for i in reversed(A):
            biggest_from_right.append(biggest)
            biggest = max(i, biggest)
        biggest_from_right.reverse()
        
        total_water = 0
        for idx,element in enumerate(A):
            elevation = min(biggest_from_left[idx], biggest_from_right[idx])
            if element < elevation:
                total_water += elevation - element
        return total_water

    # pre-allocating memory could make the solution faster
    def trap2(self, A):
        if not A:
            return 0
        sizeA = len(A)
            
        biggest = -1
        biggest_from_left = [0] * sizeA
        for idx,element in enumerate(A):
            biggest_from_left[idx] = biggest
            biggest = max(element, biggest)
        
        biggest = -1
        biggest_from_right = [0] * sizeA
        for idx,element in enumerate(reversed(A)):
            biggest_from_right[sizeA-idx-1] = biggest
            biggest = max(element, biggest)
        
        total_water = 0
        for idx,element in enumerate(A):
            elevation = min(biggest_from_left[idx], biggest_from_right[idx])
            if element < elevation:
                total_water += elevation - element
        return total_water        