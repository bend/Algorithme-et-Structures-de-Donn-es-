package tree;

import java.util.ArrayList;
import java.util.Iterator;

import stack.EmptyStackException;
import stack.NodeStack;
import stack.Stack;
import tokenizer.ExpTokenModel;
import tokenizer.Tokenizer;
import tokenizer.TokenizerModel;
import exception.ExpressionException;

public class ExpressionTree extends LinkedRBinaryTree<String> implements
		FormalExpressionTree {

	public ExpressionTree() {
		super();
	}

	/**
	 * constructeur prend en parametre un String et construit l'arbre
	 * syntaxique.
	 * 
	 * @param e
	 * @throws ExpressionException
	 */
	public ExpressionTree(String e) throws ExpressionException {
		super();
		try {
			TokenizerModel tokenizer = new Tokenizer(e);
			ArrayList<ExpTokenModel> tokens = tokenizer.tokenize();
			Stack<ExpressionTree> s = new NodeStack<ExpressionTree>();
			if (tokens.size() == 1
					&& (isNumber(tokens.get(0).toString()) || tokens.get(0)
							.toString().equals("x"))) {
				setElement(tokens.get(0).toString());
				return;
			}
			Iterator<ExpTokenModel> it = tokens.iterator();
			ExpressionTree topTree = null;
			while (it.hasNext()) {
				ExpTokenModel t = it.next();
				if (t.isOpeningParenth()) {
					s.push(new ExpressionTree());
				} else if (t.isClosingParenth()) {
					topTree = s.pop();
					if (topTree.root().element() == null) {
						topTree = (ExpressionTree) topTree.leftTree();
					}
					if (s.isEmpty()) {
						if (it.hasNext()) {
							throw new ExpressionException(
									"Invalid Expression: last parenthese is not last token!");
						}
					} else {
						s.top().addOperand(topTree);
					}
				} else if (t.isOperand()) {
					ExpressionTree tree = new ExpressionTree();
					tree.setElement(t.toString());
					s.top().addOperand(tree);
				} else if (t.isFunction()) {
					s.push(new ExpressionTree());
					s.top().setElement(t.toString());
				} else if (t.isOperator()) {
					if (s.top().root().element() != null)
						throw new ExpressionException("Duplicate Operator "
								+ t.toString());
					s.top().setElement(t.toString());
				}

			}
			if (s.isEmpty()) {
				setElement(topTree.root().element());
				setLeft(topTree.leftTree());
				setRight(topTree.rightTree());
			} else {
				setElement(s.top().root().element());
				setLeft(s.top().leftTree());
				setRight(s.top().rightTree());
			}
		} catch (EmptyStackException e2) {
			throw new ExpressionException("Expression Error");
		} catch (NullPointerException e3) {
			throw new ExpressionException("Expression Error");
		}
		if (root().element() == null)
			throw new ExpressionException("Invalid Expression " + e);
	}

	@Override
	public FormalExpressionTree derive() {
		return (FormalExpressionTree) derive(this);
	}

	private RBinaryTree<String> derive(RBinaryTree<String> root) {
		if (root.root().element().equals("+")
				|| root.root().element().equals("-"))
			return deriveOpPlusOuMoins(root);
		if (root.root().element().equals("*"))
			return deriveOpMul(root);
		if (root.root().element().equals("/"))
			return deriveOpDiv(root);
		if (root.root().element().equals("sin"))
			return deriveOpSin(root);
		if (root.root().element().equals("cos"))
			return deriveOpCos(root);
		if (root.root().element().equals("^"))
			return deriveOpPuissance(root);
		if (root.root().element().equals("x")) {
			ExpressionTree tree = new ExpressionTree();
			tree.setElement("1");
			return tree;
		}
		if (isNumber(root.root().element())) {
			ExpressionTree tree = new ExpressionTree();
			tree.setElement("0");
			return tree;
		}
		return null;
	}

	private boolean isNumber(String nextToken) {
		try {
			Integer.parseInt(nextToken);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private RBinaryTree<String> deriveOpPlusOuMoins(RBinaryTree<String> root) {
		ExpressionTree tree = new ExpressionTree();
		tree.setElement(root.root().element());
		tree.setLeft(derive(root.leftTree()));
		tree.setRight(derive(root.rightTree()));
		return tree;
	}

	private RBinaryTree<String> deriveOpMul(RBinaryTree<String> root) {
		ExpressionTree left = (ExpressionTree) root.leftTree();// f
		ExpressionTree right = (ExpressionTree) root.rightTree();// g

		ExpressionTree tree = new ExpressionTree();
		tree.setElement("+");

		ExpressionTree temp1 = new ExpressionTree();// f'g
		ExpressionTree temp2 = new ExpressionTree();// g'f

		temp1.setElement("*");
		temp2.setElement("*");

		temp1.setLeft(derive(left));
		temp1.setRight(right);

		temp2.setRight(derive(right));
		temp2.setLeft(left);

		tree.setLeft(temp1);
		tree.setRight(temp2);

		return tree;
	}

	private RBinaryTree<String> deriveOpDiv(RBinaryTree<String> root) {
		ExpressionTree left = (ExpressionTree) root.leftTree();// f
		ExpressionTree right = (ExpressionTree) root.rightTree();// g

		ExpressionTree tree = new ExpressionTree();
		tree.setElement("/");

		ExpressionTree temp1 = new ExpressionTree();// f'g
		ExpressionTree temp2 = new ExpressionTree();// g'f
		ExpressionTree leftTree = new ExpressionTree();
		ExpressionTree rightTree = new ExpressionTree();

		temp1.setElement("*");
		temp2.setElement("*");

		temp1.setLeft(derive(left));
		temp1.setRight(right);

		temp2.setRight(derive(right));
		temp2.setLeft(left);

		leftTree.setElement("-");
		leftTree.setLeft(temp1);
		leftTree.setRight(temp2);

		rightTree.setElement("*");
		rightTree.setLeft(right);
		rightTree.setRight(right);

		tree.setRight(rightTree);
		tree.setLeft(leftTree);

		return tree;
	}

	private RBinaryTree<String> deriveOpSin(RBinaryTree<String> root) {
		ExpressionTree tree = new ExpressionTree();
		ExpressionTree right = new ExpressionTree();

		right.setElement("cos");
		right.setRight(root.rightTree());

		tree.setElement("*");
		tree.setLeft(derive(root.rightTree()));
		tree.setRight(right);
		return tree;
	}

	private RBinaryTree<String> deriveOpCos(RBinaryTree<String> root) {
		ExpressionTree tree = new ExpressionTree();
		ExpressionTree right = new ExpressionTree();

		right.setElement("sin");
		right.setRight(negation(root.rightTree()));

		tree.setElement("*");
		tree.setLeft((derive(root.rightTree())));
		tree.setRight(right);
		return tree;
	}

	private RBinaryTree<String> negation(RBinaryTree<String> leftTree) {
		ExpressionTree tree = new ExpressionTree();
		ExpressionTree temp = new ExpressionTree();
		// temp.setElement("0");
		tree.setElement("-");
		tree.setLeft(temp);
		tree.setRight(leftTree);
		return tree;
	}

	private RBinaryTree<String> deriveOpPuissance(RBinaryTree<String> root) {
		ExpressionTree tree = new ExpressionTree();
		ExpressionTree temp = new ExpressionTree();
		ExpressionTree temp2 = new ExpressionTree();

		tree.setElement("*");
		temp.setElement("^");// f^a-1
		temp.setLeft(root.leftTree());
		temp.setRight(Moins1(root.rightTree()));

		temp2.setElement("*");
		temp2.setLeft(root.rightTree());
		temp2.setRight(temp);
		tree.setLeft(temp2);
		tree.setRight(derive(root.leftTree()));
		return tree;
	}

	private RBinaryTree<String> Moins1(RBinaryTree<String> rightTree) {
		ExpressionTree tree = new ExpressionTree();
		ExpressionTree temp = new ExpressionTree();
		tree.setElement("-");
		temp.setElement("1");
		tree.setLeft(rightTree);
		tree.setRight(temp);
		return tree;
	}

	/**
	 * Ajoute le token passé en argument comme opérande de l'arbre se trouvant
	 * au sommet de la pile. Le premier opérande ajouté devient le fils gauche,
	 * le second devient le fils droit.
	 * 
	 * @param t
	 *            le token
	 * @throws ExpressionException
	 *             si l'arbre au sommet de la pile possède déjà 2 opérandes.
	 */
	private void addOperand(ExpressionTree tree) throws ExpressionException {
		if (root().element() != null
				&& (root().element().equals("cos") || root().element().equals(
						"sin")))
			setRight(tree);
		else if (leftTree() == null) {
			setLeft(tree);
		} else if (rightTree() == null) {
			setRight(tree);
		} else {
			throw new ExpressionException(
					"Invalid Expression: operator already has 2 operands");
		}

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		toString(str);
		return str.toString();
	}

	public void toString(StringBuilder str) {
		if (root().element() != null)
			if (!isNumber(root().element()) && !root().element().equals("x")) {
				str.append("(");
				if (leftTree() != null) {
					((ExpressionTree) leftTree()).toString(str);
					str.append(root().element());
				} else
					str.append(root().element());
				if (rightTree() != null)
					if (root().element().equals("sin")
							|| root().element().equals("cos")) {
						str.append("(");
						((ExpressionTree) rightTree()).toString(str);
						str.append(")");
					} else
						((ExpressionTree) rightTree()).toString(str);

				else
					str.append(root().element());
				str.append(")");
			} else {
				if (root() != null)
					str.append(root().element());
			}
	}
}
