package find_formula;
import java.util.Scanner;

/*
 * @author Matthew Sokolovsky (https://github.com/OlvynChuru)
 * 
 * This program takes in a sequence of numbers and
 *  returns a polynomial formula behind those numbers.
 * The input and output are in the form of float arrays,
 *  but there is a method for printing an actual formula,
 *  and the main method let you manually enter the input sequence.
 *  
 *  For example, if you put "1 2 5 10" in the input,
 *   it will produce x² + 1
 *  If you put "5 7 19 77 241 595" in the input,
 *   it will produce x⁴ - 2x² + 3x + 5
 */

public class Find_Formula {

	public static String[] superscript_list = {"⁰","¹","²","³","⁴","⁵","⁶","⁷","⁸","⁹","¹⁰","¹¹","¹²","¹³","¹⁴","¹⁵","¹⁶","¹⁷","¹⁸","¹⁹"};

	/*
	 * The method below contains the algorithm.
	 * It takes in an array representing the sequence
	 *  of numbers, and it returns another array
	 *  that represents the formula.
	 * If you put in {5,   7,   19,  77,  241, 595} as the input,
	 *                ^    ^    ^    ^    ^    ^
	 *                f(0) f(1) f(2) f(3) f(4) f(5)
	 *                
	 *  it returns {5,  3,  -2, 0,  1}
	 *              ^   ^   ^   ^   ^
	 *              x⁰  x¹  x²  x³  x⁴
	 */
	
	public static float[] find(float[] sequence) {
		boolean listDone = false;
		int listLength = sequence.length, listHeight = sequence.length - 2, currentHeight = 0;
		float m;
		float[] formula = null;
		float[][] table = new float[listHeight][listLength], differ;
		for (int L = 0; L < listLength; L++)
			table[0][L] = sequence[L];

		for (int H = 0; H < listHeight && !listDone; H++)
		{
			listDone = true;
			for (int L = 0, end = listLength-2 - H; L < end; L++)
				if (table[H][L+1] - table[H][L] != table[H][L+2] - table[H][L+1])
					listDone = false;
			
			if (listDone)
				currentHeight = H;
			
			if (!listDone && H != listHeight-1)
				for (int L = 0, end = listLength-1 - H; L < end; L++)
					table[H+1][L] = table[H][L+1] - table[H][L];
		}
		if (!listDone)
			System.out.println("The program was unable to find the formula which determines the numbers you entered.");
		else
		{
			differ = new float[currentHeight+1][currentHeight+1];
			differ[0][0] = 1;
			if (currentHeight > 0)
			{
				differ[1][0] = 1;
				differ[1][1] = 2;
			}
			if (currentHeight > 1)
				for (int H = 2; H <= currentHeight; H++)
				{
					differ[H][H] = H + 1;
					for (int L = H - 1; L > 0; L--)
						differ[H][L] = differ[H-1][L] + differ[H-1][L-1];
					differ[H][0] = 1;
				}
			formula = new float[currentHeight+2];
			formula[0] = table[currentHeight][0];
			formula[1] = table[currentHeight][1] - table[currentHeight][0];
			for (int i = 2; i < formula.length; i++)
				formula[i] = 0;
			m = 0;
			for (int i = 1; i <= currentHeight; i++)
			{
				for (int j = i; j >= 0; j--)
				{
					m = formula[j] / differ[j][j];
					formula[j+1] = m;
					for (int k = j; k >= 0; k--)
						formula[k] -= differ[j][k] * m;
				}
				formula[0] = table[currentHeight - i][0];
			}

		}
		return formula;
	}
	
	/*
	 * The purpose of the method below is to print
	 *  out the formula array like an actual formula.
	 * If formula is {5, 3, -2, 0, 1},
	 *  then it will print out x⁴ - 2x² + 3x + 5
	 */
	
	public static void printFormula(float[] formula) {
		for (int i = formula.length - 1; i >= 0; i--)
			if (formula[i] != 0)
			{
				if (formula[i] < 0 && i == formula.length - 1)
					System.out.print("-");
				if (i > 0 && Math.abs(formula[i]) == 1);
				else if (formula[i] == Math.floor(formula[i]))
					System.out.print((long)Math.abs(formula[i]));
				else
					System.out.print(Math.abs(formula[i]));
				if (i > 0)
					System.out.print("x");
				if (i > 1 && i < superscript_list.length)
					System.out.print(superscript_list[i]);
				else if (i >= superscript_list.length)
					System.out.print("^" + i);
				System.out.print(" ");
				boolean found = false;
				for (int j = i - 1; j >= 0 && !found; j--)
					if (formula[j] != 0)
					{
						found = true;
						if (formula[j] > 0)
							System.out.print("+ ");
						else if (formula[j] < 0)
							System.out.print("- ");
					}
			}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numberAmount = 0;
		float[] sequence;
		
		System.out.println("Enter the amount of numbers that you will enter next. You must enter at least 3.");
		while (numberAmount < 3) {
			numberAmount = scan.nextInt();
			if (numberAmount < 3)
				System.out.println("You must enter at least 3.");
		}
		sequence = new float[numberAmount];
		
		System.out.println("Now enter " + numberAmount + " numbers. You can put spaces between them or put one on each line.");
		for (int L = 0; L < numberAmount; L++)
			sequence[L] = scan.nextFloat();
		
		printFormula(find(sequence));
	}

}
