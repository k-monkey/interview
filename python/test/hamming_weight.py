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