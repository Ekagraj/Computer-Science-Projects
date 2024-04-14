
/*
 Names - Ekagra Jain
 Section - C
 Pledge - I Pledge my honor that I have abided by the Stevens Honor System.
*/

import java.util.Arrays;

public class BinaryNumber {

    // Initiated a private class, a array and an Integer
    private int[] data;
    private int length;

    public BinaryNumber(int length) {
        // This constructor creates a binary number of length and consists only of zeros
        this.length = length;
        data = new int[length];
        if (length == 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid Index");
        }
    }

    public BinaryNumber(String str) {
        // This constructor creates a binary number given in String and converts it into its numerical value.
        length = str.length();
        data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = Character.getNumericValue(str.charAt(i));
        }
    }

    public int getLength() {
        // Determines the length of the Binary number
        return length;
    }

    public int[] getInnerArray() {
        // Returns the integer array representing the binary number.
        return data;
    }
    public int getDigit(int index) {
        // Obtains a digit of a binary number given an index
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds ");
        }
        return data[index];
    }

    public int toDecimal() {
        // Converts the binary number into its decimal notation
        // Example 1010 = 10
        int decimal = 0;
        int power = length - 1;

        for (int i = 0; i < length; i++) {
            decimal += data[i] * Math.pow(2, power);
            power--;
        }
        return decimal;
    }

    public void bitShift(int direction, int amount) {
        // This operation shifts all digits in a binary number any number of places to the left or right.
        if (direction != -1 && direction != 1) {
            throw new IllegalArgumentException("Invalid");
        }
        if (amount == 0 || amount < 0) {
            throw new IllegalArgumentException("Invalid");
        }
        int[] arrayName = new int[length];

        if (direction == 1) {
            // Right shift
            arrayName = new int[length - amount];
            for (int i = 0; i < arrayName.length; i++) {
                arrayName[i] = data[i];
            }
            data = arrayName;
            length = data.length;
        }
        if (direction == -1) {
            // Left shift
            arrayName = new int[length+amount];
            for (int i = 0; i < length; i++) {
                arrayName[i] = data[i];
            }
            data = arrayName;
            length = data.length;
        }

    }

    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
        // This operation computes the bitwise OR of the two numbers
        // Example: The bitwise or "1010" and "1100" will give "1110"
        if (bn1.getLength() != bn2.getLength()) {
            throw new IllegalArgumentException("Invalid input");
        }
        int[] ArrayNum = new int[bn1.getLength()];
        for (int i = 0; i < bn1.getLength(); i++) {
            if (bn1.data[i] == 1 || bn2.data[i] == 1) {
                ArrayNum[i] = 1;
            } else {
                ArrayNum[i] = 0;
            }

        }
        return ArrayNum;
    }

    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
        // This operation computes the bitwise AND of the two numbers
        // Example: The bitwise and "1010" and "1100" will give "1000"

        if (bn1.getLength() != bn2.getLength()) {
            throw new IllegalArgumentException("Invalid input");
        }
        int[] ArrayNum = new int[bn1.getLength()];
        for (int i = 0; i < bn1.getLength(); i++) {
            if (bn1.data[i] == 1 && bn2.data[i] == 1) {
                ArrayNum[i] = 1;
            } else {
                ArrayNum[i] = 0;
            }

        }
        return ArrayNum;

    }

    public String toString() {
        // This operation returns the BinaryNumber as the correspond in encoded string.
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(data[i]);
        }
        return str.toString();
    }

    public void prepend(int amount) {
        // The below operations adds 0's at the empty spaces if required.
        int[] arrayNum = new int[length + amount];
        for (int i = 0; i < length; i++) {
            arrayNum[i + amount] = data[i];
        }
        data = arrayNum;
        length = data.length;
    }

    public void add(BinaryNumber aBinaryNumber) {
        // This operation adds the two binary number even if their length is different
        // For Example: if the two numbers are 10101 and other is 1010 then it converts 1010 to 01010 to match the length and then add them.
        int GivenLength = Math.max(this.getLength(),aBinaryNumber.getLength());
        if (GivenLength>this.getLength()) {
            this.prepend(GivenLength -this.getLength());
        }
        if (GivenLength>aBinaryNumber.getLength()) {
            aBinaryNumber.prepend(GivenLength-aBinaryNumber.getLength());
        }
        // The below code if is to find the result and the carry during the addition
        /* suppose while adding the last digits of 1000 and 1001 which is 1 and 0. The carry values is 0
           The submission of the three numbers which is 1,0,0 is 1. The modulus of 1 with 2 is 1 so the result
            would be 1 and 1/2 is 0 so the carry value is 0.
        */

        int carry = 0;
        for (int i = GivenLength-1; i >= 0; i--) {
            int bit1 = this.getDigit(i);
            int bit2 = aBinaryNumber.getDigit(i);

            int tempSum = bit1+bit2 + carry;
            this.getInnerArray()[i] = tempSum%2;
            carry = tempSum/2;
        }
        // This functions prepends the last carry number
        if (carry>0) {
            this.prepend(1);
            this.getInnerArray()[0] = carry;
        }
    }

    public static void main(String[] args) {
        // Initialize binary numbers array
        int[] binaryNumber = {1, 0, 1, 0};


        int decimalValue = toDecimal(binaryNumber);


        System.out.println("Binary number: " + binaryToString(binaryNumber) + " -> Decimal value: " + decimalValue);
    }


}






