package designModel23.createModel.FactoryMethod.simpleFactory;

public class FactoryTest {
	
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}
}
