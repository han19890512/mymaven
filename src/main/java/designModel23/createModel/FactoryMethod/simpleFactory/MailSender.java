package designModel23.createModel.FactoryMethod.simpleFactory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		 System.out.println("this is mailsender!");
	}

}
