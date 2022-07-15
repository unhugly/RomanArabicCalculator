import java.util.List;

class LineHandler {
    static List<Character> Operators = List.of( '+', '-', '*', '/' );
    static List<Character> RomanSymbols = List.of( 'i', 'v', 'x' );
    static List<Character> ArabicSymbols = List.of( '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' );

    static ArgumentsTriplet<String, Character, String> HandleExpression(String str) throws Throwable{
        String exp = ClearLineOfSpaces(str.toLowerCase());
        CheckValid(exp);
        return new ArgumentsTriplet<String, Character, String>(FindLeftOperand(exp), DetermineOperator(exp), FindRightOperand(exp));
    }

    private static String FindLeftOperand(String input) {
        StringBuilder result = new StringBuilder(input);
        return result.delete(FindOperatorIndex(result.toString()), result.length()).toString();
    }

    private static String FindRightOperand(String input) {
        StringBuilder result = new StringBuilder(input);
        return result.delete(0, FindOperatorIndex(result.toString())+1).toString();
    }

    private static char DetermineOperator(String str) throws Throwable{
        for (int i = 0; i < Operators.size(); ++i){
            for (char c : str.toCharArray()){
                if (c == Operators.get(i))
                    return Operators.get(i);
            }
        }
        throw new Exception("Operator was't found");
    }

    private static void CheckValid(String str) throws Throwable{
        System.out.println("Validating input..");
        if (str == null) throw new Exception("Uninitialized input");
        if (str == "") throw new Exception("Empty line");

        StringBuilder resultOperator = new StringBuilder();
        for (int i = 0; i < str.toCharArray().length; ++i)
            for (char op : Operators)
                if (op == str.toCharArray()[i])
                    resultOperator.append(str.toCharArray()[i]);
        if (resultOperator.length() > 1) throw new Exception("More than 1 operator");
        if (resultOperator.length() < 1) throw new Exception("Operator wasn't found");

        for (int i = 0; i < str.length(); ++i)
            if (str.toCharArray()[i] == resultOperator.charAt(0))
                if (i == 0 || i == str.length() - 1)
                    throw new Exception("Operator must be between two operands");

        StringBuilder resultSymbols = new StringBuilder(str);
        resultSymbols.delete(FindOperatorIndex(str), FindOperatorIndex(str)+1);

        for (char c : String.valueOf(resultSymbols).toCharArray())
            if (!RomanSymbols.contains(c) && !ArabicSymbols.contains(c))
                throw new Exception("Operands wasn't found /or/ Illigal symbols");

        if (IsContains(resultSymbols.toString(), ArabicSymbols) && IsContains(resultSymbols.toString(), RomanSymbols))
            throw new Exception("It is not possible to use both numerals(actually does, but task had other conditions)");
        System.out.println("Validate completed!");
    }

    private static String ClearLineOfSpaces(String str) {
        StringBuilder result = new StringBuilder();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == ' ')
                continue;
            result.append(arr[i]);
        }
        return result.toString();
    }

    private static int FindOperatorIndex(String input) {
        int operIndex = -1;
        for (int i = 0; i < input.toCharArray().length; ++i)
            for (char op : Operators)
                if (input.toCharArray()[i] == op)
            operIndex = i;
        return operIndex;
    }

    private static boolean IsContains(String str, List<Character> list){
        for (char c : str.toCharArray())
            if (list.contains(c)) return true;
        return false;
    }
}
