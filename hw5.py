'''
Created on __2nd December 2023__
@author:   __Ekagra Jain__
Pledge:    __I pledge my honor that I have abided by the Stevens Honor System.__

CS115 - Hw 5 
'''
memo={}
def fast_lucas(n):
    '''Returns the nth Lucas number using the memoization technique
    shown in class and lab. The Lucas numbers are as follows:
    [2, 1, 3, 4, 7, 11, ...]'''

    def fast_lucas(n, memo):
        if n in memo:
            return memo[n]
        elif n == 0:
            lucas = 2
        elif n == 1:
            lucas = 1
        else:
            lucas = fast_lucas(n-1,memo) + fast_lucas(n-2,memo)
        memo[n] = lucas
        return lucas
    return fast_lucas(n,{})

memo = {}
def fast_change(amount, coins):
    '''Takes an amount and a list of coin denominations as input.
    Returns the number of coins required to total the given amount.
    Use memoization to improve performance.'''

    if (amount,tuple(coins)) in memo:
        return memo[(amount, tuple(coins))]
    if amount <= 0:
        return 0
    if not coins:
        return float("inf")
    elif coins[0]> amount:
        change = fast_change(amount, coins[1:])
        memo[(amount, tuple(coins))] = change
        return change
    else:
        use_it = 1+ fast_change(amount - coins[0], coins)
        lose_it = fast_change(amount, coins[1:])
    memo[(amount, tuple(coins))] = min(use_it,lose_it)
    return memo[(amount, tuple(coins))]

# If you did this correctly, the results should be nearly instantaneous.
print(fast_lucas(3))  # 4
print(fast_lucas(5))  # 11
print(fast_lucas(9))  # 76
print(fast_lucas(24))  # 103682
print(fast_lucas(40))  # 228826127
print(fast_lucas(50))  # 28143753123

print(fast_change(131, [1, 5, 10, 20, 50, 100]))
print(fast_change(292, [1, 5, 10, 20, 50, 100])) 
print(fast_change(673, [1, 5, 10, 20, 50, 100]))
print(fast_change(724, [1, 5, 10, 20, 50, 100]))
print(fast_change(888, [1, 5, 10, 20, 50, 100]))

