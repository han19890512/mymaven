package designModel23.createModel.Singleton;

/**
 * instance = new Singleton();语句是分两步执行的。 但是 JVM 并不保证这两个操作的先后顺序
 * 
 * @author admin
 * 
 */
public class Singleton2 {

	/* 持有私有静态实例，防止被引用，此处赋值为 null，目的是实现延迟加载 */
	private static Singleton2 instance = null;

	/* 私有构造方法，防止被实例化 */
	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		if (instance == null) {
			synchronized (instance) {//加载方法上每次会对instance加锁--会影响性能，所以先判断
				if (instance == null) {
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
}
