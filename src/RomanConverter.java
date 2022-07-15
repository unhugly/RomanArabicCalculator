import java.util.ArrayList;
import java.util.List;

class RomanConverter {
    List<String> RomansNumerals = List.of( "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i" );
    List<Integer> ArabicsNumerals = List.of( 100, 90, 50, 40, 10, 9, 5, 4, 1 );
    List<String> RomansDictionary;

    RomanConverter(){
        System.out.println("Loading..");
        RomansDictionary = new ArrayList<String>();
        for (int i = 0; i <= 100; ++i) { // 100 - максимально возможный результат при выполнении арифметических операций в данном Т/З
            RomansDictionary.add(initializeRomansValues(i));
        }
        System.out.println("Load complete!");
    }

    String initializeRomansValues(int arabic){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ArabicsNumerals.size(); ++i)
            while (arabic >= ArabicsNumerals.get(i)) {
                arabic -= ArabicsNumerals.get(i);
                result.append(RomansNumerals.get(i));
            }
        return result.toString().toUpperCase();
    }

    public String a2R(int arabic) {
        if (arabic == 0) return "N(0)";
        else return RomansDictionary.get(arabic);
    }

    public int r2A(String roman) {
        return RomansDictionary.indexOf(roman.toUpperCase());
    }

}
