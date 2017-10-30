package designModel23.constructionModel.Bridge;
/**
 * 就通过对Bridge 类的调用，实现了对接口Sourceable 的实现类 
 * SourceSub1 和SourceSub2 的调用。
 * 因为这个是我们JDBC 连接的原理，有数据库学 习基础的，一结合就都懂了。
 * @author admin
 *
 * 此模式只是调用
 */
public class BridgeTest {
	public static void main(String[] args) {
		Bridge bridge = new MyBridge();
		 /*调用第一个对象*/
		Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();
		
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
		
	}
}
