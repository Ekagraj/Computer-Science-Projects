

#Name: Ekagra Jain
#I pledge my honor that I have abided by the Stevens honor System

""" The Board_game(board) prints the Tic-Tac-Toe board and assigns an index to each position of the 4X4 board. """

def Board_game(board):
    print(board[0] + " | " + board[1] + " | " + board[2] + " | " + board[3])
    print("-" * 13)
    print(board[4] + " | " + board[5] + " | " + board[6] + " | " + board[7])
    print("-" * 13)
    print(board[8] + " | " + board[9] + " | " + board[10] + " | " + board[11])
    print("-" * 13)
    print(board[12] + " | " + board[13] + " | " + board[14] + " | " + board[15])

""" The code checks various conditions to determine if a player has won the game based on the moves played. """
def Win_conditions(board):

    """ The code checks various conditions to determine if the rows are eligible to win """
    if board[0] == board[1] == board[2] == board[3] and board[1] != "-" or board[4] == board[5] == board[6] == board[7] and board[5] != "-" or board[8] == board[9] == board[10] == board[11] and board[8] != "-" or board[12] == board[13] == board[14] == board[15] and board[13] != "-":
        return True

    """ The code checks various conditions to determine if the columns are eligible to win  """
    if board[0] == board[4] == board[8] == board[12] and board[0] != "-" or board[1] == board[5] == board[9] == board[13] and board[5] != "-" or board[2] == board[6] == board[10] == board[14] and board[2] != "-" or board[3] == board[7] == board[11] == board[15] and board[3] != "-" :
        return True

    """ The code checks the conditions to determine if the two possible diagonals are eligible to win """
    if board[0] == board[5] == board[10] == board[15] and board[15] != "-" or board[3] == board[6] == board[9] == board[12] and board[3] != "-" :
        return True
    return False

""" 1. The play_move takes two parameters: current_Player and board.
    2. The function ask the user to enter a move which should be a number between 1 and 16.
    3. It then checks if the move is valid by verifying that the input entered is an integer and is within the range of 1 to 16.
    4. It also checks whether that specific cell on the board is empty "-".
    5. If the move is not valid, an error message is printed and the player is asked to enter a valid move which once entered, the function returns the move. """
    
def play_move(current_Player, board):
    is_valid = False
    move_int = False
    while not is_valid:
        try:
            move = int(input("Player " + current_Player + ", enter any number from 1 to 16: "))
        except:
            while move_int==False:
                print("Its an Illegal move !, Enter between 1 to 16 only ")
                move = input("Player " + current_Player + ", enter any number from 1 to 16: ")
                try:
                    move=int(move)
                    move_int=True
                except: continue
            
        if move >= 1 and move <= 16 and board[move-1] == "-":
             is_valid = True   
        else:
            print("Its an Illegal move ! ")
            if move >= 1 and move <= 16 and board[move-1] != "-":
                print("The space is already occupied! ")          
    return move


""" 1. The function play_game() is used to start the game of Tic-Tac-Toe.
    2. The function initializes the game board with empty spaces and asks the user to choose either "X" or "O".
    3. It then switches between the players, asking each player to make a move by calling the play_move() function.
    4. The function checks if a player has won the game by calling the Win_conditions() function, and if not, it switches the current player to the other player.
    5. If the game ends in a draw, it means each space in the board is occupied.
    6. If the game ends in a draw or is won by a player, the function asks the user if they want to play again."""

def play_game():
    
    """ Board assigns empty spaces for the Tic-Tac-Toe board """
    
    board = ['-', '-', '-','-','-', '-', '-','-','-', '-', '-','-','-', '-', '-','-']
    Board_game(board)
    valid = False
    while not valid:
        choose = input("Enter X or O ? ")
        current_Player = choose.upper()
        if current_Player == 'X' or current_Player == 'O':
            valid = True
        elif current_Player != 'X' and current_Player != 'O':    
            print("Invalid move, Enter between X or O only")
    winner = None
    while not winner:
        move = play_move(current_Player, board)
        board[move - 1] = current_Player
        Board_game(board)
        winner = Win_conditions(board)
        if not winner:
            if current_Player == "X":
                current_Player = "O"
            else:
                current_Player = "X"
            if "-" not in board:
                print("No one wins. It's a Draw.")
                play_again = input("Do you want to play the game again ? Type 'Y' for Yes or 'N' for No ")
                if play_again.lower() == 'y' :
                    play_game()
                elif play_again.lower() == 'n' :
                    print("Thank you for playing a good game! Good Byee!!")
                    break
                
                while play_again.lower() not in ('y', 'n'):
                    play_again = input("Do you want to play the game again ? Type 'Y' for Yes or 'N' for No ")
                    if play_again.lower() == 'y' :
                        play_game()
                    elif play_again.lower() == 'n' :
                        print("Thank you for playing a good game! Good Byee!!")
                        break
                break    
        elif winner:
            print("Congratulations! Player " + current_Player + " wins!")
            play_again = input("Do you want to play the game again ? Type 'Y' for Yes or 'N' for No ")
            if play_again.lower() == 'y' :
                play_game()
            elif play_again.lower() == 'n' :
                print("Thank you for playing a good game! Good Byee!!")
                break
            
            while play_again.lower() not in ('y', 'n'):
                play_again = input("Do you want to play the game again ? Type 'Y' for Yes or 'N' for No ")
                if play_again.lower() == 'y' :
                    play_game()
                elif play_again.lower() == 'n' :
                    print("Thank you for playing a good game! Good Byee!!")
                    break
            
play_game()
