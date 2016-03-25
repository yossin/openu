import java.util.Random;

public class Password{
	private final static int AlphabetLen ='z'-'a';
	private final String password;
	public Password(int length){
		Random random = new Random();
		char pass[]=new char[length]; 
		for (int i=0;i<length; i++){
			char b=random.nextBoolean()?'A':'a';
			pass[i]=(char)(b+(Math.abs(random.nextInt())%AlphabetLen)); 
		}
		this.password=new String(pass);
	}
	public String getPassword() {
		return password;
	}
	public boolean isPassword(String pass){
		return password.equals(pass);
	}
}