import unittest

from src.trap_rain_water import Solution

class MyTest(unittest.TestCase):
    def test_A(self):
        solution = Solution()
        input1 = [0,1,0,2,1,0,1,3,2,1,2,1]
        self.assertEqual(6, solution.trap(input1))
        input2 = []
        self.assertEqual(0, solution.trap(input2))       
        input3 = [0,0,0]
        self.assertEqual(0, solution.trap(input3))        
        input4 = None
        self.assertEqual(0, solution.trap(input4))   
        input5 = [1,2,3,4,5,6]
        self.assertEqual(0, solution.trap(input5))   
        input6 = [6,5,4,3,2,1]
        self.assertEqual(0, solution.trap(input6))   
        
if __name__ == '__main__':
    fooSuite = unittest.TestLoader().loadTestsFromTestCase(MyTest)
    fooRunner = unittest.TextTestRunner(descriptions=True)
    fooRunner.run(fooSuite)