

public class Tree 
{
	Node root;
	public Tree(Node root)
	{
		this.root = root;
	}
}
class Node
{
	Node leftChild;
	Node rightChild;
	Board board;
	public Node(Node leftChild, Node rightChildi, Board board)
	{
		this.rightChild = rightChild;
		this.leftChild = leftChild;
		this.board = board;
	}
}
