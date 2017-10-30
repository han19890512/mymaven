import org.junit.Test;


public class Test2 {
	private String zcc="";
	@Test
	public  void main() {
		h(0,"111111111111111111000000");
		System.err.println(zcc);
	}
	public void h(int a,String s){
		if(s.contains("1")){
			String subs=s.substring(s.indexOf("1"));
			int begin=s.indexOf("1")+a;
			int end=24;
			if(subs.contains("0"))
				 end=subs.indexOf("0")+begin;
			setZcc(getZcc() + ((begin+1)+"-"+end+"周"));
			h(end,s.substring(begin-a).substring(end-begin));
		}
	}
	public String getZcc() {
		return zcc;
	}
	public void setZcc(String zcc) {
		this.zcc = zcc;
	}
}
