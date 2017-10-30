package designModel23.constructionModel.Facade;
/**
 * 外观模式是为了解决类与类之家的依赖关系的，
 * 像spring 一样，可以将类和类之间的关系配置到配置 文件中，
 * 而外观模式就是将他们的关系放在一个Facade 类中，
 * 降低了类类之间的耦合度，该模式中没 有涉及到接口
 * @author admin
 *
 */
public class User {
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.startup();
		computer.shutdown();
	}
}
