class Calculator {
    public RomanConverter Converter;
    public int MinInput  = 1;
    public int MaxInput  = 10;

    public Calculator() {
        Converter = new RomanConverter();
    }

    public String NextExpression(String str) throws Throwable {
        StringBuilder result = new StringBuilder();
        var exp = LineHandler.HandleExpression(str);
        System.out.println("Calculating..");
        if (IsRoman(exp.x)) {
            result.append(CalculateRoman(exp));
        }
        else {
            result.append(Calculate(exp));
        }
        System.out.println("Calculated!");
        return result.toString();
    }

    private boolean IsRoman(String str) {
        for (char c : str.toCharArray())
            return Converter.RomansNumerals.contains(String.valueOf(c));
        return false;
    }

    private String CalculateRoman(ArgumentsTriplet<String,Character,String> exp) throws Throwable {
        var tempTuple = new ArgumentsTriplet<String,Character,String>(
                String.valueOf(Converter.r2A(exp.x)),
                exp.y,
                String.valueOf(Converter.r2A(exp.z)));
        int result = Integer.parseInt(Calculate(tempTuple));
        if (result < 0) throw new Exception("There are no negative numbers in the Roman numeral system");
        return Converter.a2R(result);
    }

    private String Calculate(ArgumentsTriplet<String,Character,String> exp) throws Throwable {
        if (IsInRange(exp)) throw new Exception("Input must be in range from 1 to 10 || from I to X");
        String result = null;
        switch (exp.y) {
            case '+':
                result = String.valueOf(Integer.parseInt(exp.x) + Integer.parseInt(exp.z));
                break;
            case '-':
                result = String.valueOf(Integer.parseInt(exp.x) - Integer.parseInt(exp.z));
                break;
            case '*':
                result = String.valueOf(Integer.parseInt(exp.x) * Integer.parseInt(exp.z));
                break;
            case '/':
                result = String.valueOf(Integer.parseInt(exp.x) / Integer.parseInt(exp.z));
                break;
            default:
                throw new Exception("Wrong operator");
        }
        return result;
    }

    private boolean IsInRange(ArgumentsTriplet<String,Character,String> exp) {
        return (Integer.parseInt(exp.x) > MaxInput || Integer.parseInt(exp.x) < MinInput)
            || (Integer.parseInt(exp.z) > MaxInput || Integer.parseInt(exp.z) < MinInput);
    }
}
