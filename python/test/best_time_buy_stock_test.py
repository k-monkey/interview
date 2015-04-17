import unittest

from src.best_time_buy_stock import Solution

class MyTest(unittest.TestCase):
    def test_A(self):
        solution = Solution()
        # self.assertEquals(0, solution.maxProfit(2, [0,0,0,0]))
        # self.assertEquals(3, solution.maxProfit(2, [1,2,3,4]))        
        # self.assertEquals(2, solution.maxProfit(2, [1,2,3,1]))        
        # self.assertEquals(0, solution.maxProfit(2, [4,3,2,1]))        
        self.assertEquals(6, solution.maxProfit(2, [4,3,2,5,2,5]))        
        
if __name__ == '__main__':
    fooSuite = unittest.TestLoader().loadTestsFromTestCase(MyTest)
    fooRunner = unittest.TextTestRunner(descriptions=True)
    fooRunner.run(fooSuite)    