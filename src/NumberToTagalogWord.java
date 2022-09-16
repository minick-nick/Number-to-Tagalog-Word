import java.util.Scanner;

public class NumberToTagalogWord {

    private static final String[] tensNames = {
        "", " sampu", " dalawampu", " tatlumpu", " apatnapu", " limampu", " animnapu",
        " pitumpu", " walumpu", " siyamnapu" 
    };
    
    private static final String[] numNames = {
        "", " isa", " dalawa", " tatlo", " apat", " lima", " anim", " pito",
        " walo", " siyam", " sampu", " labing-isa", " labaindalawa", " labintatlo",
        " labing-apat", " labinlima", " labing-anim", " labimpito", " labing-walo", " labinsiyam"
    };

    public static String[] getSuffix(int num) {
        String []suffix = {"", "", ""};

        if(num % 10 >= 1 && num % 10 <= 9) {
            suffix[0] = "'t";
        }
        
        if ( (num >= 400 && num <= 499) ||
             (num >= 600 && num <= 699) ||
             (num >= 900 && num <= 999) ) {
            
            suffix[1] = " na";

            if(num % 100 != 0) {
                suffix[2] = "raa't";
            } else {
                suffix[2] = "raan";
            }


        } else {
            suffix[1] = "ng";

            if(num > 100 && num % 100 != 0) {
                suffix[2] = "daa't";
            } else {
                suffix[2] = "daan";
            }
        }
        
        return suffix;
    }

    public static String convertToTagalogWord(int num) {
        // -1000 to 1000
        
        String sign = "";
        String word = "";
        
     
        if( num < -1000 || num > 1000) {
            return "Di-wastong input";
        } else if(num == 0) {
            return "Sero";
        } else if(num == 1000) {
            return "Isang libo";
        } else if(num == -1000) {
            return "Negatibo isang libo";
        }

        if(num < 0) {
            sign = "negatibo ";
            num = Math.abs(num);
        }

        String []suffix = getSuffix(num);

        if(num % 100 < 20) {
            word = numNames[num % 100];
            num /= 100;
        } else {
            word = numNames[num % 10];
            num /= 10;
            word = tensNames[num % 10]+ suffix[0] + word;
            num /= 10;
        }

        if(num != 0) {
            word = numNames[num] + suffix[1] + " " + suffix[2] + word; 
        }

        word = (sign + word).trim().replaceAll(" +", " ");
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static void main(String[] args) {   

        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int num = in.nextInt();


        System.out.println(num + " in Tagalog: " + convertToTagalogWord(num));
    }
}