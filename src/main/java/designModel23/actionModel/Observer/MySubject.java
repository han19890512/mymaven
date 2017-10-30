package designModel23.actionModel.Observer;

public class MySubject extends AbstractSubject {
	
	public void operation() {
		System.out.println("update self!");
		String message="111";//通知所有观察的消息
		notifyObservers(message);
	}
}
