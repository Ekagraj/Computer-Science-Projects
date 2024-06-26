'''
Created on __10th October 2023_____
@author:   _____Ekagra Jain__________________
Pledge:    _I pledge my honor that I have abided by the Stevens Honor System._______

CS115 - Hw 3
'''
# Be sure to submit hw3.py.  Remove the '_template' from the file name.

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 0
' Implement the function giveChange() here:
' See the PDF in Canvas for more details.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
# your code goes here

# Here's the list of letter values and a small dictionary to use.
# Leave the following lists in place.
scrabbleScores = \
   [ ['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2],
     ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], ['m', 3], ['n', 1],
     ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1],
     ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10] ]

Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar', 'babble', 'can', 'foo',
              'spam', 'spammy', 'zzyzva']

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 1
' Implement wordsWithScore() which is specified below.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def letterScore(letter, scorelist):

    """ This function takes as input a single letter string called letter and a list
         where each element in that list is itself a list of the form """
    
    if scorelist == []:
        return 0
    if scorelist[0][0] == letter:
        return scorelist[0][1]
    else:
        return letterScore(letter, scorelist[1:])
    
def wordScore(S, scorelist):
    
    """ This function finds the scrabble score of the string S passed as a parameter, and returns it as a output using the scorelist."""
    
    if S == '':
        return 0
    else:
        return letterScore(S[0],scorelist)+ wordScore(S[1:],scorelist)

def wordsWithScore(dct, scores):
    '''List of words in dct, with their Scrabble score.

    Assume dct is a list of words and scores is a list of [letter,number]
    pairs. Return the dictionary annotated so each word is paired with its
    value. For example, wordsWithScore(Dictionary, scrabbleScores) should
    return [['a', 1], ['am', 4], ['at', 2] ...etc... ]
    '''
    if dct == [] or scores == []:
        return []
    else:
        return [[dct[0],wordScore(dct[0],scores)]]+ wordsWithScore(dct[1:],scores)



'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 2
' For the sake of an exercise, we will implement a function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
' (Notice that you cannot assume anything about the length of the list.)
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def take(n, L):
    '''Returns the list L[0:n], assuming L is a list and n is at least 0.'''
    if n == 0:
        return []
    if n > len(L):
        return L
    else:
        return [L[0]] + take(n-1,L[1:])


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 3
' Similar to problem 2, will implement another function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def drop(n, L):
    '''Returns the list L[n:], assuming L is a list and n is at least 0.'''
    if L == []:
        return []
    elif n ==0:
        return [L[0]]+ L[1:]
    else:
        return drop(n-1,L[1:])

def giveChange(amount, coins):

    """ This function returns the list whose first item is the minimum number of coins and whose second item is a
         list of the coins in that optimal solution """

    if amount <= 0:
        return [0,[]]
    if not coins:
        return [float("inf"),[]]
    elif coins[0] > amount:
        return giveChange(amount, coins[1:])
    else:
        use_it = giveChange(amount - coins[0], coins)
        lose_it = giveChange(amount, coins[1:])
        
        if use_it[0]+1 < lose_it[0]:
            return [use_it[0]+1, [coins[0]]+ use_it[1]]
        else:
            return lose_it



    


