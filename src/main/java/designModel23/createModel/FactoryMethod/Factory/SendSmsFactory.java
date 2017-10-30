package designModel23.createModel.FactoryMethod.Factory;

public class SendSmsFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		 return new SmsSender();
	}

}
