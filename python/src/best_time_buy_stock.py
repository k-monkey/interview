# Say you have an array for which the ith element is the price of a given stock on day i.

# Design an algorithm to find the maximum profit. You may complete at most k transactions.

# Note:
# You may not engage in multiple transactions at the same time (ie, you must sell the 
# stock before you buy again).

# Credits:
# Special thanks to @Freezen for adding this problem and creating all test cases.

#hint: dynamic programming

class Solution:
    # @return an integer as the maximum profit 
    def maxProfit(self, k, prices):
        if (not prices) or (k <= 0):
            return float("-inf")
            
        #Init two 2D tables hold[I, k], and release[I, k]
        hold = [len(prices)*[float("-inf")] for i in range(k)]
        release = [len(prices)*[float("-inf")] for i in range(k)]        
        
        #construct the DP table.        
        #release[n][m] = prices[m] + Max(hold[n][j]) for all j<m
        #hold[n][m] = -prices[m] + Max(release[n-1][q]) for all q<u
        #hold[0][*] = -prices[*], first row of hold[]
        result = 0
        for n in range(k):
            for m in range(len(prices)):
                if n == 0:
                    hold[0][m] = -prices[m]
                else:
                    if m == 0:
                        hold[n][0] = -prices[0]
                    else:
                        hold[n][m] = -prices[m] + max(release[n-1][0:m])
                        
                if m >= 1:
                    release[n][m] = prices[m] + max(hold[n][0:m])
                    if release[n][m] > result:
                        result = release[n][m]
        return result
        
