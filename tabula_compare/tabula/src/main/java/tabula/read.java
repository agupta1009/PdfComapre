package tabula;
import java.io.File;
import java.util.Scanner;

public class read {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(new File("C:/Users/ankush.gupta/Desktop/cordinates.pdf"));
//		Scanner sc=new Scanner(new File("C:/Users/ankush.gupta/Desktop/result.pdf"));
		int count=0;
		while(sc.hasNextLine())
		{
			System.out.println(sc.nextLine());
			count++;
		}
		System.out.println(count);


	}

}
