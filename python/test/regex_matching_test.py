#!/usr/bin/python
# vim: set fileencoding=utf-8
import unittest
import sys

from src.regex_matching import Solution

class MyTest(unittest.TestCase):
    def test_A(self):
        solution = Solution()
        self.assertTrue(solution.isMatch("aa", "aa"))
        self.assertFalse(solution.isMatch("aa", "a"))
        self.assertFalse(solution.isMatch("aaa", "aa"))
        self.assertTrue(solution.isMatch("aa", "a*"))
        self.assertTrue(solution.isMatch("aa", ".*"))
        self.assertTrue(solution.isMatch("ab", ".*"))
        self.assertTrue(solution.isMatch("aab", "c*a*b"))
        self.assertTrue(solution.isMatch("aab", "a*e*b"))
        self.assertTrue(solution.isMatch("aab", ".*ab"))
        self.assertFalse(solution.isMatch("aab", "...b"))
        self.assertTrue(solution.isMatch("ab", ".b"))

if __name__ == '__main__':
    fooSuite = unittest.TestLoader().loadTestsFromTestCase(MyTest)
    fooRunner = unittest.TextTestRunner(descriptions=True)
    fooRunner.run(fooSuite)