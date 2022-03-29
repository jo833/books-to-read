import java.util.LinkedList;

public class BackEnd implements BackEndInterface {
	protected static class Node<BookData> {
		public BookDataInterface data;
		public Node<BookData> parent; // null for root node
		public Node<BookData> leftChild;
		public Node<BookData> rightChild;

		public Node(BookDataInterface data) {
			this.data = data;

		}

		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

		@Override
		public String toString() {
			String output = "[";
			LinkedList<Node<BookData>> q = new LinkedList<>();
			q.add(this);
			while (!q.isEmpty()) {
				Node<BookData> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
			return output + "]";
		}
	}

	protected Node<BookData> root; // reference to root node of tree, null when empty
	protected int size = 0; // the number of values in the tree

}
