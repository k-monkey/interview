import sys
import unittest

class FooTest(unittest.TestCase):
    """Sample test case"""
    
    # preparing to test
    def setUp(self):
        """ Setting up for the test """
        print "FooTest:setUp_"
    
    # ending the test
    def tearDown(self):
        """Cleaning up after the test"""
        print "FooTest:tearDown_"
    
    # test routine A
    #@unittest2.skip("FooTest:test_A:skipped")
    def test_A(self):
        """Test routine A"""
        self.skipTest("FooTest:test_A:skipped")
        print "FooTest:test_A"
    
    # test routine B
    def test_B(self):
        """Test routine B"""
        fooA = 123
        fooB = 234
        self.assertEqual(fooA, fooB, "A is not equal to B")
        print "FooTest:test_B"
    
    # test routine C
    def test_C(self):
        """Test routine C"""
        fooA = 123
        fooB = 123
        self.assertEqual(fooA, fooB, "A is not equal to B")
        print "FooTest:test_C"
    
    # test routine D
    def test_D(self):
        """Test routine D"""
        self.fail("FooTest:test_D:fail_")
        print "FooTest:test_D"

# Run the test case
if __name__ == '__main__':
    fooSuite = unittest.TestLoader().loadTestsFromTestCase(FooTest)
    fooRunner = unittest.TextTestRunner(descriptions=True)
    fooRunner.run(fooSuite)
