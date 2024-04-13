'''
Created on _1st November 2023_
@author:   _Ekagra Jain_
Pledge:    _I pledge my honor that I have abided by the Stevens Honor System_

CS115 - Hw 6
'''
# Number of bits for data in the run-length encoding format.
# The assignment refers to this as k.
COMPRESSED_BLOCK_SIZE = 5

# Number of bits for data in the original format.
MAX_RUN_LENGTH = 2 ** COMPRESSED_BLOCK_SIZE - 1

# Do not change the variables above.
# Write your functions here. You may use those variables in your code.

from functools import reduce

def isOdd(n):
    '''Returns whether or not the integer argument is odd.'''
    
    if n%2==0:
        return False
    else:
        return True

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

def add(x,y):
    
    """ This function adds two values."""
    
    return x+y
    
def bits_5(S):

    """ This function converts binary number into a 5 bits expression."""
    
    if len(S) < COMPRESSED_BLOCK_SIZE:
        return "0" * (COMPRESSED_BLOCK_SIZE - len(S)) + S
    else:
        return S

def times(S):

    """ This function tells how many times the first occured consecutively."""
    
    if S == "":
        return 0
    if len(S) == 1:
        return 1
    if S[0] == S[1]:
        return 1 + times(S[1:])
    else:
        return 1

def lst(S):

    """ This function makes a list of the consecutive numbers in times functions """
    
    if S == "":
        return []   
    else:
        X = times(S)
        return [X] + lst(S[X:])

def Split_num(L):

    """ This function maintains the max run length of the function. This is done by spliting it."""
    
    if L == []:
        return []
    if L[0] > MAX_RUN_LENGTH:
        L[0] = L[0] - MAX_RUN_LENGTH
        return [MAX_RUN_LENGTH,0] + Split_num(L)
    else:
        return [L[0]] + Split_num(L[1:])


def compress(S):

    """ that takes a binary string S of length 64 as input and returns another binary string as output."""
    
    if S == "":
        return 0
    if S[0] == "1":
        return "0" * COMPRESSED_BLOCK_SIZE + reduce(add, map(bits_5, map(numToBinary, Split_num(lst(S)))))
    else:
        return reduce(add, map(bits_5, map(numToBinary, Split_num(lst(S)))))

def uncompress(C, n = "0"):

    """This function "inverts" or "undoes" the compressing in your compress function."""

    if len(C) == COMPRESSED_BLOCK_SIZE:
        return n * binaryToNum(C)
    else:
        if n == "0":
            return n * binaryToNum(C[:COMPRESSED_BLOCK_SIZE]) + uncompress(C[COMPRESSED_BLOCK_SIZE:],n = "1")
        if n == "1":
            return n * binaryToNum(C[:COMPRESSED_BLOCK_SIZE]) + uncompress(C[COMPRESSED_BLOCK_SIZE:],n = "0")

def compression(S):

    """ This return the ratio of the compressed size to the original size for image S."""
    
    return len(compress(S))/len(S)


##Comments##

# Let the String be 01010101
# This String is of 8 bits. While running the code it has been represnted in the form of "0*8" which indicated 8 consecutive zeros and ones "0" and "1"
# Now lets increase the length of the string by 16 bits which would be 0101010101010101. This is similar to the above one but the pattern is repeated twice.
# Therefore, it is represnted in (0*16) indication 16 consecutive zeros and ones ("0" and "1")
# Though this we can see that the compressed string is longer than the original string due to repetition.
# This shows that sometimes paterns with "0" and "1" may cause run length encoding to create a longer sequence. Which is not effecient. 

    
    


    





    

