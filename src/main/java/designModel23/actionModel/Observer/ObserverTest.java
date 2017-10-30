package designModel23.actionModel.Observer;

public class ObserverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());

		sub.operation();

	}

}
