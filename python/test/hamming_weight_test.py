import sys
import unittest

from hamming_weight import Solution

class HammingWeightTest(unittest.TestCase):
    """test for hamming_weight, also known as Number of 1 bit"""
    
    # test routine A
    def test_A(self):
        hw = Solution()
        self.assertEqual(hw.hamming_weight(100), 3)
        self.assertEqual(hw.hamming_weight(-101), 4)
        self.assertEqual(hw.hamming_weight('100'), -1)
        self.assertEqual(hw.hamming_weight(2), 1)
        self.assertEqual(hw.hamming_weight(1), 1)
        self.assertEqual(hw.hamming_weight(0), 0)
        
if __name__ == '__main__':
    fooSuite = unittest.TestLoader().loadTestsFromTestCase(HammingWeightTest)
    fooRunner = unittest.TextTestRunner(descriptions=True)
    fooRunner.run(fooSuite)