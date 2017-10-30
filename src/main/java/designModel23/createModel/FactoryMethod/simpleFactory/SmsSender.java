package designModel23.createModel.FactoryMethod.simpleFactory;

public class SmsSender implements Sender {

	@Override
	public void Send() {
		 System.out.println("this is sms sender!");
	}

}
