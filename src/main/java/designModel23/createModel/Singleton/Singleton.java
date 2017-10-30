package designModel23.createModel.Singleton;
/**
 * 单例模式使用内部类来维护单例的实现，
 * JVM 内部的机制能够保证当一个类被加载的时 候，
 * 这个类的加载过程是线程互斥的。这样当我们第一次调用getInstance 的时候，
 * JVM 能够帮我们保 证 instance 只被创建一次，
 * 并且会保证把赋值给instance 的内存初始化完毕
 * @author admin
 *
 */
public class Singleton {

	/* 私有构造方法，防止被实例化 */
	private Singleton() {
	}

	/* 此处使用一个内部类来维护单例 */
	private static class SingletonFactory {
		private static Singleton instance = new Singleton();
	}

	/* 获取实例 */
	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}
}
