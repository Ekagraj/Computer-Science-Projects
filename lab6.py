'''
Created on _18th October 2023__
@author:   _Ekagra Jain___
Pledge:    __I pledge my honor that I have abided by the Stevens Honor System._

CS115 - Lab 6
'''
def isOdd(n):
    '''Returns whether or not the integer argument is odd.'''
    
    if n%2==0:
        return False
    else:
        return True

# If N is odd, the base of N becomes Y + "1" and if N is even, base becomes Y + "0" 
# 10 = 2^3*1 + 2^2*0 + 2^1 *1 + 2^0 *0 = 1010
# For Odd base-10 number, the rightmost bit will be 1 in its base-2 as 2^0 = 1
# For Even base-10 number, the rightmost bit will be 0 in its base-2
# 1010 whose value is 10. After eliminating 0 i.e the rightmost digit,we get 101 which has a value equivalent to 5. Thus we are doing here integer division by 2

def numToBinary(n):
    '''Precondition: integer argument is non-negative.
    Returns the string with the binary representation of non-negative integer n.
    If n is 0, the empty string is returned.'''
    
    if n == 0:
        return ""
    elif isOdd(n):
        return numToBinary(n//2) + "1"
    else:
        return numToBinary(n//2) + "0"

def binaryToNum(s):
    '''Precondition: s is a string of 0s and 1s.
    Returns the integer corresponding to the binary representation in s.
    Note: the empty string represents 0.'''
    
    if s == "":
        return 0
    else:
        return binaryToNum(s[:-1]) * 2 + int(s[-1])

def increment(s):
    '''Precondition: s is a string of 8 bits. Returns the binary representation of binaryToNum(s) + 1.'''
    
    if s == "11111111":
        return "00000000"
    if s == 1:
        return 0
    else:
        return "0" * (8 - len(numToBinary(binaryToNum(s) + 1))) + numToBinary(binaryToNum(s) + 1)
 
def count(s, n):
    '''Precondition: s is an 8-bit string and n >= 0.
    Prints s and its n successors.'''
    
    print(s)
    if n == 0:
        return ""
    else:
        return count(increment(s),n-1)

def numToTernary(n):
    '''Precondition: integer argument is non-negative.
    Returns the string with the ternary representation of non-negative integer
    n. If n is 0, the empty string is returned.'''

    #10 = 3^2*1 +3^1*0 + 3^0*1 = 101
    
    if n == 0:
        return ""
    else:
        return numToTernary(n//3) + str(n%3)


def ternaryToNum(s):
    '''Precondition: s is a string of 0s, 1s, and 2s.
    Returns the integer corresponding to the ternary representation in s.
    Note: the empty string represents 0.'''
    
    if s == "":
        return 0
    else:
        return ternaryToNum(s[:-1]) * 3 + int(s[-1])

