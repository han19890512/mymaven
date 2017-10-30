import org.junit.Test;


public class Test1 {
	private String zcc;
	@Test
	public void test01(){
		Integer iAmNull = null; 
		if(iAmNull instanceof Integer){ 
		   System.out.println("iAmNull is instance of Integer");                              		  
		}else{ 
		   System.out.println("iAmNull is NOT an instance of Integer"); 
		} 
	}
	@Test
	public  void main() {
		System.out.println(Integer.valueOf("127")==Integer.valueOf("127"));
		System.out.println(Integer.valueOf("128")==Integer.valueOf("128"));
		System.out.println(Integer.parseInt("128")==Integer.valueOf("128"));
	}
	
//	@Test
//	public void h(String s){
//		if(s.contains("1")){
//			int begin=s.indexOf("1");
//			int end=s.substring(s.indexOf("1")).indexOf("0")+begin;
//			zcc+=begin+"-"+end+"周";
//			h(s.substring(s.indexOf("1")).substring(s.substring(s.indexOf("1")).indexOf(0)));
//		}
//	}
	@Test
	public void a(){
		byte b=(byte)121;
		System.out.println(b);
	}
}
