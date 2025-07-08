class Solution {
    // These are lookup arrays to easily get the word for a number.
    // They are "final" because they will never change.

    // Words for numbers 1-19, which have unique names.
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };

    // Words for the tens place (20, 30, etc.).
    private final String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };

    // Words for the scale of the number (Thousand, Million, Billion).
    private final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        // This is a special case. If the number is 0, the answer is just "Zero".
        if (num == 0) {
            return "Zero";
        }

        int i = 0; // This will keep track of which THOUSANDS word to use (index 0, 1, 2...).
        String words = ""; // This will store our final result string.

        // We will process the number in chunks of 3 digits from right to left.
        while (num > 0) {
            // Check if the current 3-digit chunk is not zero.
            // This prevents results like "One Million Zero Thousand...".
            if (num % 1000 != 0) {
                // Convert the 3-digit chunk to words using a helper function,
                // add the correct THOUSANDS word (e.g., "Thousand", "Million"),
                // and add it to the front of our result.
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            }

            // Move to the next 3-digit chunk to the left.
            num /= 1000;
            // Move to the next THOUSANDS word.
            i++;
        }

        // The process above can leave extra spaces, so trim() cleans them up.
        return words.trim();
    }

    // This helper function converts any number less than 1000 into words.
    private String helper(int num) {
        // If the number is 0, we don't need to say anything.
        if (num == 0) {
            return "";
        }
        // Numbers less than 20 have unique names (like "Thirteen").
        // We can just look them up in our LESS_THAN_20 array.
        else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        }
        // For numbers from 20 to 99 (e.g., 56).
        else if (num < 100) {
            // We get the tens word ("Fifty") and add the word for the ones place ("Six").
            return TENS[num / 10] + " " + helper(num % 10);
        }
        // For numbers from 100 to 999 (e.g., 123).
        else {
            // We get the hundreds word ("One" + "Hundred") and add the words for the rest
            // ("Twenty Three").
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}

//
class Solution {
    public int calculate(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int currnum = 0;
        char lastoper = '+';
        for (int i = 0; i < len; i++) {
            char currchar = s.charAt(i);
            if (Character.isDigit(currchar)) {
                currnum = (currnum * 10) + currchar - '0';
            }
            if (!Character.isDigit(currchar) && !Character.isWhitespace(currchar) || i == len - 1) {
                if (lastoper == '+') {
                    stack.push(currnum);
                } else if (lastoper == '-') {
                    stack.push(currnum);
                } else if (lastoper == '*') {
                    stack.push(stack.pop() * currnum);
                } else if (lastoper == '/') {
                    stack.push(stack.pop() / currnum);
                }
                lastoper = currchar;
                currnum = 0;
            }

        }
        int result = 0;
        while (!stack.isEmpty()) {
            result = result + stack.pop();
        }
        return result;

    }
}