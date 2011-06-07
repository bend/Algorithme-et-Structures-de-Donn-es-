package tree;

public class SimplePosition<E> implements Position<E> {

	private E element;

	public SimplePosition() {
		element = null;
	}

	@Override
	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

}
