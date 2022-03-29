import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

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

	@Override
	public boolean containsBook(BookData book) {

		if (contains(book) == true) {
			return true;
		}

		return false;
	}

	@Override
	public String findTitles(int bookRank) {

		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getTitle();
		}
	}

	protected Node<BookData> lookupHelper(int bookRank, Node<BookData> current) {
		if (current == null) {
			return null;
		}

		if (current.data.getRank() == bookRank) {

			return current;

		} else if (current.data.getRank() > (bookRank)) {
			lookupHelper(bookRank, current.leftChild);
		} else if (current.data.getRank() < (bookRank)) {
			lookupHelper(bookRank, current.rightChild);

		}

		return current;
	}

	@Override
	public String findGenre(int bookRank) {
		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getGenre();
		}
	}

	@Override
	public String findYearPublished(int bookRank) {
		List<String> years = new LinkedList<>();
		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getYearPublished();
		}
	}

	@Override
	public String findAuthor(int bookRank) {

		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getAuthor();
		}
	}

	@Override
	public int findAmountSold(int bookRank) {
		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getAmountSold();
		}

	}

	@Override
	public double findPriceOfBook(int bookRank) {
		lookupHelper(bookRank, root);

		if (size() == 0) {
			throw new NoSuchElementException("No Book Found");

		}

		else {
			return root.data.getPriceOfBook() * 1.30;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	public boolean contains(BookData book) {

		if (book == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		return this.containsHelper(book, root);
	}

	private boolean containsHelper(BookData movie, Node<BookData> subtree) {
		if (subtree == null) {

			return false;
		} else {
			int compare = Integer.toString(movie.getRank()).compareTo(Integer.toString(subtree.data.getRank()));
			if (compare < 0) {

				return containsHelper(movie, subtree.leftChild);
			} else if (compare > 0) {

				return containsHelper(movie, subtree.rightChild);
			} else {

				return true;
			}
		}
	}

	@Override
	public Iterator<BookData> iterator() {

		return new Iterator<BookData>() {

			Stack<Node<BookData>> stack = null;
			Node<BookData> current = root;

			public BookData next() {

				if (stack == null) {
					stack = new Stack<Node<BookData>>();
					current = root;
				}

				while (current != null) {
					stack.push(current);
					current = current.leftChild;
				}

				if (!stack.isEmpty()) {
					Node<BookData> processedNode = stack.pop();
					current = processedNode.rightChild;
					return (BookData) processedNode.data;
				} else {

					throw new NoSuchElementException("There are no more elements in the tree");
				}

			}

			public boolean hasNext() {

				return !(current == null && (stack == null || stack.isEmpty()));
			}

		};
	}

	@Override
	public String toString() {

		Iterator<BookData> treeNodeIterator = this.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if (treeNodeIterator.hasNext())
			sb.append(treeNodeIterator.next());
		while (treeNodeIterator.hasNext()) {
			BookData data = treeNodeIterator.next();
			sb.append(", ");
			sb.append(data.toString());
		}
		sb.append(" ]");
		return sb.toString();
	}

	@Override
	public boolean insert(BookDataInterface book) {
		Node<BookData> newBook = new Node<BookData>(book);

		if (root == null) { // Add newMovie to an empty MovieTree

			root = newBook;

			size++;
			return true;
		} else { // Add newMovie to an non-empty MovieTree

			boolean flag = insertBookHelper(newBook, root);
			if (flag == true) {
				size++;
				return true;
			} else {
				return false;
			}

		}
	}

	private boolean insertBookHelper(Node<BookData> newBook, Node<BookData> current) {

		if (newBook.data.getRank() == (current.data.getRank())) {
			return false;
		}

		else if (newBook.data.getRank() < current.data.getRank()) {

			if (current.leftChild == null) {
				current.leftChild = newBook;
				newBook.parent = current;

				return true;

			} else
				return insertBookHelper(newBook, current.leftChild);
		}

		else {
			if (current.rightChild == null) {
				current.rightChild = newBook;
				newBook.parent = current;

				return true;

			} else
				return insertBookHelper(newBook, current.rightChild);
		}
	}

}
