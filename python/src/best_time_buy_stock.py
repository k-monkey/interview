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
            return 0
        
        #simple optimization before engaging into DP 
        if k >= len(prices)//2:
            return sum(i-j for i, j in zip(prices[1:], prices[:-1]) if i-j > 0)

        #Init two 2D tables hold[I, k], and release[I, k]
        hold = len(prices)*[float("-inf")]
        release = len(prices)*[float("-inf")]        
        
        #construct the DP table.        
        #release[n][m] = prices[m] + Max(hold[n][j]) for all j<m
        #hold[n][m] = -prices[m] + Max(release[n-1][q]) for all q<u
        #hold[0][*] = -prices[*], first row of hold[]
        result = 0
        for n in range(k):
            (max_hold, max_release) = (float("-inf"), float("-inf"))
            for m in range(len(prices)):
                if n == 0:
                    hold[m] = -prices[m]
                else:
                    hold[m] = -prices[m] + max_release
                        
                max_release = max(max_release, release[m])
                release[m] = prices[m] + max_hold
                max_hold = max(max_hold, hold[m])
                if release[m] > result:
                    result = release[m]
        return result
        
    def maxProfit2(self, k, prices):
        if k >= len(prices)//2:
            return sum(i-j for i, j in zip(prices[1:], prices[:-1]) if i-j > 0)
        hold, release = [float('-inf')]*(k+1), [0]*(k+1)
        for p in prices:
            for i in range(1, k+1):
                release[i] = max(release[i], hold[i]+p)
                hold[i] = max(hold[i], release[i-1]-p)
        return release[k]
        
