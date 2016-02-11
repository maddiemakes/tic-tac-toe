

public class Tree 
{
	TreeNode root;
	public Tree(TreeNode root)
	{
		this.root = root;
	}
}
class TreeNode
{
	TreeNode leftChild;
	TreeNode rightChild;
	int board[][];
	public TreeNode(int board[][])
	{
		this.board = board;
	}
}
