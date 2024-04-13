########################################################
"""
Name - Ekagra Jain
Pledge - I Pledge my honor that I have abided by the stevens honor system
Lab - 2
"""
########################################################

def dot(L,K):

    """ This function returns the dot product of two lists with same length """

    
    if len(L) == len(K):
        if L == [] or K == []:
            return 0.0
        else:
            return L[0]*K[0] + dot(L[1:],K[1:])
    else:
        return "Try Entering the list with same lengths"

def explode(S):

    """ This function returns the string into a list """

    if S == "":
        return []

    else:
        return list(S[0]) + explode(S[1:])

def ind(e,L):

    """ This function returns the index of e from L """
    
    if L == []:
        return 0
    if str(L) == "":
        return 0
    if L[0] == e:
        return 0
    if str(L[0]) == e:
        return 0

    else:
        return ind(e,L[1:])+1

def removeAll(e,L):

    """This function return then list of L with does not contain e """
    
    if L == []:
        return []
    if L[0] == e:
        return [L[1]] + removeAll(e,L[2:])
    else:
        return [L[0]] + removeAll(e,L[1:])

def myFilter(x,L):

    """ This function inputs two parameters. the first is a function and its filters the list """
    
    if L == []:
        return []
    if (x(L[0]) == True):
        return [L[0]] + myFilter(x,L[1:])
    else:
        return myFilter(x,L[1:])        

def deepReverse(L):

    """This function reverse the list from the end to the first one"""

    if L == []:
        return []
    if isinstance(L[0], list):
        return deepReverse(L[1:]) + [deepReverse(L[0])]
    else:
        return deepReverse(L[1:])+[L[0]]

    


    


