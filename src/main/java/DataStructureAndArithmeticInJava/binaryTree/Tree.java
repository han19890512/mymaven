package DataStructureAndArithmeticInJava.binaryTree;

/**
 * 二叉树
 * @author 韩世邦
 *
 * @param <E>
 */

public class Tree<E> {

	private Node<E> root;

	
	public Tree() {
		super();
	}

	public Node<E> find(int key) {
		Node<E> current = root;

		while (current.key != key) {

			if (current.key < key) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == null)
				return null;
		}

		return current;
	}

	public void insert(Node<E> node) {
		if (root == null) {
			root = node;
		} else {
			Node<E> current = root;
			Node<E> parent;
			while (true) {
				parent = current;
				if (node.key < current.key) {
					current = current.leftChild;
					if (current == null)
						parent.leftChild = node;
					//return;
				} else {
					current = current.rightChild;
					if (current == null)
						parent.rightChild = node;
					//return;
				}
			}
		}
	}
	
	public Node<E> delete(int key) {
		Node<E> current = root;

		while (current.key != key) {

			if (current.key < key) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == null)
				return null;
		}

		return current;
	}
	
	public boolean remove(int  key) {
		
		return false;
	}

	public void inOrder(Node<E> node){
		if(node!=null){
			inOrder(node.leftChild);//左
			System.out.println(node.item);//自己
			inOrder(node.rightChild);//右
		}
	}
	
	public Node<E> miniNode(){
		Node<E> current,miniNode;
		miniNode=current=root;
		while(current!=null){
			miniNode=current;
			current=current.leftChild;
		}
		
		return miniNode;
	}
	
	private static class Node<E> {

		E item;// 数据包
		Integer key;// 标记
		Node<E> leftChild;// 左侧节点
		Node<E> rightChild;// 右侧节点
	}
}
