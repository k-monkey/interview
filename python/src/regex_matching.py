# vim: set fileencoding=utf-8

# '.' Matches any single character.
# '*' Matches zero or more of the preceding element.

# The matching should cover the entire input string (not partial).

# The function prototype should be:
# bool isMatch(const char *s, const char *p)

# Some examples:
# isMatch("aa","a") → false
# isMatch("aa","aa") → true
# isMatch("aaa","aa") → false
# isMatch("aa", "a*") → true
# isMatch("aa", ".*") → true
# isMatch("ab", ".*") → true
# isMatch("aab", "c*a*b") → true
import re

#NOTES: the in this question is the '*' char.
#1. 'c*' could match to nothing, example isMatch("aab", 'c*a*b') = True
#2. whenever an '*' is encountered, recursion or DP must be used to handle:
# 2a. '*' matches nothing
# 2b. '*' matches one char or multiple chars.
class Solution:
    # @return a boolean
    def lazyMatch(self, s, p):
        '''a lazy implementation using python's re lib'''
        exp = re.compile('^' + p + '$')
        return exp.match(s)

    # @return a boolean
    def isMatch(self, s, p):
        remaining_p = p
        while remaining_p and s:
            (pattern, remaining_p) = self.next_pattern(remaining_p)
            if pattern == None:
                raise Exception("Invalid matching pattern: " + p)
            if pattern.endswith('*'):
                if self.isMatch(s, remaining_p):
                    #account for case of zero occurrence of pattern 
                    return True 
                for i in range(len(s)):
                    if pattern.startswith('.') or pattern[0] == s[i]:
                        if self.isMatch(s[i+1:], remaining_p):
                            #one combination (splitting at idx i) matches the strings
                            #FIXME: we could use Dynamic Programming here.
                            #to save the pre-computed matching result in an boolean array
                            return True
                #all possible combinations failed to match
                return False
            else:
                if pattern == '.' or pattern[0] == s[0]:
                    #one char matched, remove it
                    s = s[1:]
                else:
                    return False
        return (s == '') and (remaining_p == '')
    
    def next_pattern(self, p):
        pattern = p[0]
        if pattern == '*':
            return None
        if len(p) > 1 and p[1] == '*':
            pattern += '*'
            return (pattern, p[2:])
        else:
            return (pattern, p[1:])
                