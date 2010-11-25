package avltreepack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class AVLTree<K, V> {

	private Entry<K, V> root;
	private AVLTree<K, V> leftTree, rightTree, parentTree;
	private final Comparator<K> C;

	/*
	 * Constructor for a subtree
	 */
	public AVLTree(AVLTree<K, V> parent) {
		root = null;
		leftTree = null;
		rightTree = null;
		parentTree = parent;
		C = new DefaultComparator<K>();
	}

	// source :
	// http://www.jarvana.com/jarvana/view/com/extjs/gxt/1.0.1/gxt-1.0.1.jar!/com/extjs/gxt/ui/client/util/DefaultComparator.java?format=ok
	private class DefaultComparator<X extends Object> implements Comparator<X> {

		@Override
		public int compare(Object o1, Object o2) {

			if (o1 instanceof Float) {
				return ((Float) o1).compareTo(((Float) o2));
			} else if (o1 instanceof Double) {
				return ((Double) o1).compareTo(((Double) o2));
			} else if (o1 instanceof Short) {
				return ((Short) o1).compareTo(((Short) o2));
			} else if (o1 instanceof Integer) {
				return ((Integer) o1).compareTo(((Integer) o2));
			} else if (o1 instanceof Long) {
				return ((Long) o1).compareTo(((Long) o2));
			} else {
				return compareStrings(o1.toString(), o2.toString());
			}
		}

		protected int compareStrings(String s1, String s2) {
			return s1.toLowerCase().compareTo(s2.toLowerCase());
		}

	}

	/*
	 * return true if the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * return the number of entries in the tree
	 */
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

	/*
	 * return the height
	 */
	public int getHeight() {
		if (isEmpty())
			return 0;
		else
			return 1 + Math
					.max(leftTree().getHeight(), rightTree().getHeight());
	}

	/*
	 * return the entry in the root
	 */
	public Entry<K, V> root() {
		return root;
	}

	public AVLTree<K, V> leftTree() {
		return leftTree;
	}

	public AVLTree<K, V> rightTree() {
		return rightTree;
	}

	public AVLTree<K, V> parentTree() {
		return parentTree;
	}

	public void setLeft(AVLTree<K, V> tree) {
		leftTree = tree;
	}

	public void setRight(AVLTree<K, V> tree) {
		rightTree = tree;
	}

	public void setParent(AVLTree<K, V> tree) {
		parentTree = tree;
	}

	/*
	 * Test if the key is valid
	 */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException();
	}

	/*
	 * If D contains an entry with key equal to k then return such an entry,
	 * else return null
	 */
	public Entry<K, V> get(K key) throws InvalidKeyException {
		checkKey(key);
		int compare = C.compare(key, root().getKey());
		if (compare < 0 && leftTree().root() != null) {
			return (leftTree()).get(key);
		}

		else if (compare > 0 && rightTree().root() != null) {
			return (rightTree()).get(key);
		}

		else if (compare == 0) {
			return root();
		} else {
			return null;
		}

	}

	/*
	 * Return an iterable collection containing all entries with key equal to k
	 */
	public Iterable<Entry<K, V>> getAll(K key) throws InvalidKeyException {
		checkKey(key);
		LinkedList<Entry<K, V>> entries = new LinkedList<Entry<K, V>>();
		getAll2(entries, this, key);
		return entries;
	}

	private void getAll2(LinkedList<Entry<K, V>> entries, AVLTree<K, V> tree,
			K key) {
		int compare = C.compare(key, tree.root().getKey());
		if (compare < 0 && tree.leftTree().root() != null) {
			getAll2(entries, tree.leftTree(), key);
		}

		else if (compare > 0 && tree.rightTree().root() != null) {
			getAll2(entries, tree.rightTree(), key);
		}

		else if (compare == 0) {
			entries.add(tree.root());
			if (tree.leftTree().root() != null) {
				getAll2(entries, tree.leftTree(), key);
			}
		}
	}

	public AVLTree<K, V> tallerChild() {
		if (leftTree().size() < rightTree().size())
			return rightTree();
		else
			return leftTree();

	}

	public void rebalance(AVLTree<K, V> tree, DicoTree<K, V> dico) {
		AVLTree<K, V> z = tree;
		while (z != null) {
			if (!z.isBalanced()) {
				restructure(z.tallerChild().tallerChild(), z.tallerChild(), z,
						dico);
			}
			z = z.parentTree();
		}

	}

	/*
	 * Insert an entry with key k and value v into D, returning the entry
	 * created
	 */
	public Entry<K, V> put(K key, V value, DicoTree<K, V> dico) {
		if (isEmpty()) {
			root = new SimpleEntry<K, V>(key, value);
			leftTree = new AVLTree<K, V>(this);
			rightTree = new AVLTree<K, V>(this);
			rebalance(this, dico);

			return root;
		} else {
			int comp = C.compare(key, root().getKey());
			if (comp <= 0) {
				return leftTree().put(key, value, dico);
			} else {
				return rightTree().put(key, value, dico);
			}
		}
	}

	/*
	 * Trinode restructuration
	 */
	public void restructure(AVLTree<K, V> x, AVLTree<K, V> y, AVLTree<K, V> z,
			DicoTree<K, V> dico) {
		System.out.println(":)");
		AVLTree<K, V> a = null;
		AVLTree<K, V> b = null;
		AVLTree<K, V> c = null;

		int compar1 = C.compare(x.root().getKey(), y.root().getKey());
		int compar2 = C.compare(y.root().getKey(), z.root().getKey());
		int compar3 = C.compare(x.root().getKey(), z.root().getKey());
		if (compar1 <= 0 && compar2 <= 0 && compar3 <= 0) // x<y<z
		{
			a = x;
			b = y;
			c = z;
		} else if (compar1 <= 0 && compar2 > 0 && compar3 <= 0) // x<z<y
		{
			a = x;
			b = z;
			c = y;
		} else if (compar1 > 0 && compar2 <= 0 && compar3 <= 0) // y<x<z
		{
			a = y;
			b = x;
			c = z;
		} else if (compar1 > 0 && compar2 <= 0 && compar3 > 0) // y<z<x
		{
			a = y;
			b = z;
			c = x;
		} else if (compar1 <= 0 && compar2 > 0 && compar3 > 0) // z<x<y
		{
			a = z;
			b = x;
			c = y;
		} else // z<y<x
		{
			a = z;
			b = y;
			c = x;
		}

		ArrayList<AVLTree<K, V>> temp = new ArrayList<AVLTree<K, V>>(4);
		temp.add(a.leftTree());
		if (a.rightTree() != b && a.rightTree() != c)
			temp.add(a.rightTree());
		if (b.leftTree() != a)
			temp.add(b.leftTree());
		if (b.rightTree() != c)
			temp.add(b.rightTree());
		if (c.leftTree() != a && c.leftTree() != b)
			temp.add(c.leftTree());
		temp.add(c.rightTree());
		AVLTree<K, V> t0 = temp.get(0);
		AVLTree<K, V> t1 = temp.get(1);
		AVLTree<K, V> t2 = temp.get(2);
		AVLTree<K, V> t3 = temp.get(3);
		temp = null;

		if (z.parentTree() == null) {
			dico.setSuperRoot(b);
		} else {
			int comp = C.compare(z.parentTree().root().getKey(), z.root()
					.getKey());
			if (comp < 0) // z est le sous arbre de droite
			{
				z.parentTree().setRight(b);
			} else {
				z.parentTree().setLeft(b);
			}
		}
		b.setLeft(a);
		b.setRight(c);
		a.setLeft(t0);
		a.setRight(t1);
		c.setLeft(t2);
		c.setRight(t3);

	}

	/*
	 * Return an iterable collection of the key-value entries in D
	 */
	public Iterable<Entry<K, V>> entrySet() {
		LinkedList<Entry<K, V>> positions = new LinkedList<Entry<K, V>>();
		if (!isEmpty()) {
			inorderTraversal(positions, this);
		}
		return positions;
	}

	public void inorderTraversal(LinkedList<Entry<K, V>> positions,
			AVLTree<K, V> tree) {
		if (!tree.leftTree().isEmpty()) {
			inorderTraversal(positions, tree.leftTree());
		}

		positions.add(tree.root());

		if (!tree.rightTree().isEmpty()) {
			inorderTraversal(positions, tree.rightTree());
		}

	}

	public boolean isBalanced() {
		if (isEmpty())
			return true;
		else if (rightTree() == null) {
			return (leftTree().getHeight() < 2);
		} else if (leftTree() == null) {
			return (rightTree().getHeight() < 2);
		} else {
			return (Math.abs(leftTree().getHeight() - rightTree().getHeight()) < 2)
					&& leftTree().isBalanced() && rightTree().isBalanced();
		}
	}

}
