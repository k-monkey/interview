# Write a function that takes an unsigned integer and returns the number 
# of ’1' bits it has (also known as the Hamming weight).

# For example, the 32-bit integer ’11' has binary representation 
# 00000000000000000000000000001011, so the function should return 3.

class Solution:
    """hamming_weight, also known as Number of 1 bit"""
            
    # @param n, an integer
    # @return an integer
    def hamming_weight(self, n):
        if not isinstance(n, (int, long)):
            return -1
        weight = 0
        for digit in "{0:b}".format(n):
            if digit == '1':
                weight += 1 
        return weight 
        
        
    def hammingWeight(self, n):
        '''a very pythonic one-liner'''
        return str(bin(n)).count('1')