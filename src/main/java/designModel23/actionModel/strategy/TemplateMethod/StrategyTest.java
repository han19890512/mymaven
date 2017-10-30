package designModel23.actionModel.strategy.TemplateMethod;

public class StrategyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String exp = "8+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(exp, "\\+");
		System.out.println(result);

	}

}
