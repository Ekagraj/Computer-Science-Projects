############################################################
# Name  :  __Ekagra Jain__
# Pledge:  __I pledge my honor that I have abided by the Stevens Honor System.__
# CS115 -  __Hw 4__
# Created on : __24 October 2023__


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 1
' Implementing pascal_row function
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def pascal_row(n):
    '''This function outputs a list of elements found in a certain row of Pascal’s Triangle.'''
    if n==0:
        return [1]
    if n==1:
        return [1,1]
    else:
        return [1] + pascal_helper(pascal_row(n-1))
    
def pascal_helper(integer):
    '''This is a Helper function which calculates the rows in pascal triangle'''
    if integer == []:
        return []
    if len(integer)==1:
        return [1]
    else:
        return [integer[0]+integer[1]] + pascal_helper(integer[1:])
   
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 2
' Implementing pascal_triangle function
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def pascal_triangle(n):
    ''' This function takes as input a single integer n and returns a list of lists containing the values of the all the rows up to and including row n.'''
    if n<0:
        return []
    else:
       return pascal_triangle(n-1) + [pascal_row(n)]


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 3
' Implementing test_pascal_row and test_pascal_triangle functions
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def test_pascal_row():
    assert pascal_row(0) == [1]
    assert pascal_row(1) == [1,1]
    assert pascal_row(2) == [1,2,1]
    assert pascal_row(3) == [1,3,3,1]
    assert pascal_row(4) == [1,4,6,4,1]
    assert pascal_row(5) == [1,5,10,10,5,1]

def test_pascal_triangle():
    assert pascal_triangle(0) == [[1]]
    assert pascal_triangle(1) == [[1], [1, 1]]
    assert pascal_triangle(2) == [[1], [1, 1], [1, 2, 1]]
    assert pascal_triangle(3) == [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1]]
    assert pascal_triangle(4) == [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
    assert pascal_triangle(5) == [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1], [1, 5, 10, 10, 5, 1]]
    
