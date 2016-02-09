

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
	public Node(Node leftChild, Node rightChild)
	{
		this.rightChild = rightChild;
		this.leftChild = leftChild;
	}
}
