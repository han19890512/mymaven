package designModel23.createModel.FactoryMethod.Factory;

public class SmsSender implements Sender {

	@Override
	public void Send() {
		 System.out.println("this is sms sender!");
	}

}
