package designModel23.createModel.FactoryMethod.Factory;

public class MailSender implements Sender {

	@Override
	public void Send() {
		 System.out.println("this is mailsender!");
	}

}
