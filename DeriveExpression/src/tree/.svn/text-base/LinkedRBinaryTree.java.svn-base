package tree;

import java.util.ArrayList;

public class LinkedRBinaryTree<E> implements RBinaryTree<E> {

	private final SimplePosition<E> root;
	private RBinaryTree<E> leftTree, rightTree;

	public LinkedRBinaryTree() {
		root = new SimplePosition<E>();
		leftTree = null;
		rightTree = null;
	}

	@Override
	public boolean isEmpty() {
		return root.element() == null && isLeaf();
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			int x = 1;
			if (leftTree() != null) {
				x += leftTree().size();
			}
			if (rightTree() != null) {
				x += rightTree().size();
			}
			return x;
		}
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public boolean isLeaf() {
		return (leftTree == null && rightTree == null);
	}

	@Override
	public RBinaryTree<E> leftTree() {
		return leftTree;
	}

	@Override
	public RBinaryTree<E> rightTree() {
		return rightTree;
	}

	@Override
	public void setElement(E o) {
		root.setElement(o);
	}

	@Override
	public void setLeft(RBinaryTree<E> tree) {
		leftTree = tree;
	}

	@Override
	public void setRight(RBinaryTree<E> tree) {
		rightTree = tree;
	}

	@Override
	public Iterable<Position<E>> positions() {
		ArrayList<SimplePosition<E>> positions = new ArrayList<SimplePosition<E>>();
		if (!isEmpty()) {
			inorderTraversal(positions);
		}
		return (Iterable<Position<E>>) positions.iterator();
	}

	@Override
	public void inorderTraversal(ArrayList<SimplePosition<E>> positions) {
		if (leftTree != null) {
			leftTree.inorderTraversal(positions);
		}
		if (!isEmpty()) {
			positions.add(root);
		}
		if (rightTree != null) {
			rightTree.inorderTraversal(positions);
		}

	}
}
