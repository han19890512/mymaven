package designModel23.constructionModel.Bridge;
/**
 * 定义一个桥，持有Sourceable 的一个实例：
 * @author admin
 *
 */
public abstract class Bridge {
	
	
	private Sourceable source;
	
	public void method(){
	    source.method();
	}

	public Sourceable getSource() {
		return source;
	}

	public void setSource(Sourceable source) {
		this.source = source;
	}
	
	
}
