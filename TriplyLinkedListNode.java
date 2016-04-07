package linkedList;

import java.sql.Timestamp;
import java.util.Date;

public class TriplyLinkedListNode {

	private TriplyLinkedListNode right;
	private TriplyLinkedListNode left;
	private TriplyLinkedListNode down;
	
	private String data;
	
	public TriplyLinkedListNode(String data) {
		this.data = data;
	}
	
	public TriplyLinkedListNode(TriplyLinkedListNode right, TriplyLinkedListNode left, TriplyLinkedListNode down, String data) {
		this.right = right;
		this.left = left;
		this.down = down;
		
		this.data = data;
	}
	
	
	public TriplyLinkedListNode getRight() {
		return right;
	}

	public void setRight(TriplyLinkedListNode right) {
		this.right = right;
	}

	public TriplyLinkedListNode getLeft() {
		return left;
	}

	public void setLeft(TriplyLinkedListNode left) {
		this.left = left;
	}

	public TriplyLinkedListNode getDown() {
		return down;
	}

	public void setDown(TriplyLinkedListNode down) {
		this.down = down;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public static TriplyLinkedListNode flattenList(TriplyLinkedListNode node) {
		//boolean firstLevel = true;
		
		TriplyLinkedListNode lastNode = node;
		while(lastNode.getRight() != null) {
			lastNode = lastNode.getRight();
			System.out.println("last node: " + lastNode.getData());
		}
		
		TriplyLinkedListNode currentNode = node;
		while(currentNode.getRight() != null || currentNode.getDown() != null) {
			if(currentNode.getDown() != null) {
				TriplyLinkedListNode rightNode = currentNode.getRight();
				if (rightNode != null) {
					rightNode.setLeft(getLast(currentNode.getDown()));
					getLast(currentNode.getDown()).setRight(rightNode);
				}
				
				currentNode.setRight(currentNode.getDown());
				currentNode.setDown(null);
			}
			currentNode = currentNode.getRight();
			System.out.println("current node: " + currentNode.getData());
		}
		
//		if(node.getRight() == null) {
//			return node;
//		}
		
		return node;
	}
	
	public static TriplyLinkedListNode flattenListRec(TriplyLinkedListNode node) {
		TriplyLinkedListNode currentNode = node;
		if(currentNode.getDown() != null) {
			TriplyLinkedListNode rightNode = currentNode.getRight();
			if (rightNode != null) {
				rightNode.setLeft(getLast(currentNode.getDown()));
				getLast(currentNode.getDown()).setRight(rightNode);
			}
			
			currentNode.setRight(currentNode.getDown());
			currentNode.setDown(null);
		}
		if(currentNode.getRight() != null) {
			flattenListRec(currentNode.getRight());
		} else {
			return node;
		}
		return node;
	}
	
	public static TriplyLinkedListNode getLast(TriplyLinkedListNode node) {
		TriplyLinkedListNode lastNode = node;
		while(lastNode.getRight() != null) {
			lastNode = lastNode.getRight();
			System.out.println("last node: " + lastNode.getData());
		}
		return lastNode;
	}

	public static void main(String[] args) {
		// creating all nodes
		TriplyLinkedListNode node1_1 = new TriplyLinkedListNode("Node1_1");
		TriplyLinkedListNode node1_2 = new TriplyLinkedListNode("Node1_2");
		TriplyLinkedListNode node1_3 = new TriplyLinkedListNode("Node1_3");
		TriplyLinkedListNode node1_4 = new TriplyLinkedListNode("Node1_4");
		
		TriplyLinkedListNode node2_1 = new TriplyLinkedListNode("Node2_1");
		TriplyLinkedListNode node2_2 = new TriplyLinkedListNode("Node2_2");
		
		TriplyLinkedListNode node2_1_1 = new TriplyLinkedListNode("Node2_1_1");
		TriplyLinkedListNode node2_1_2 = new TriplyLinkedListNode("Node2_1_2");
		TriplyLinkedListNode node2_1_3 = new TriplyLinkedListNode("Node2_1_3");
		
		TriplyLinkedListNode node2_1_3_1 = new TriplyLinkedListNode("Node2_1_3_1");
		
		TriplyLinkedListNode node4_1 = new TriplyLinkedListNode("Node4_1");
		TriplyLinkedListNode node4_2 = new TriplyLinkedListNode("Node4_2");
		
		// setting all right nodes
		node1_1.setRight(node1_2);
		node1_2.setRight(node1_3);
		node1_3.setRight(node1_4);
		
		node2_1.setRight(node2_2);
		
		node2_1_1.setRight(node2_1_2);
		node2_1_2.setRight(node2_1_3);
		
		node4_1.setRight(node4_2);
		
		// setting all left nodes
		node1_4.setLeft(node1_3);
		node1_3.setLeft(node1_2);
		node1_2.setLeft(node1_2);
		
		node2_2.setLeft(node2_1);
		
		node2_1_2.setLeft(node2_1_1);
		node2_1_3.setLeft(node2_1_2);
		
		node4_2.setLeft(node4_1);
		
		// setting all down nodes
		node1_2.setDown(node2_1);
		node2_1.setDown(node2_1_1);
		node2_1_3.setDown(node2_1_3_1);
		node1_4.setDown(node4_1);
		
		Date startDate = new Date();
		Timestamp startTimestamp = new Timestamp(startDate.getTime());
		System.out.println(startTimestamp);
		
//		TriplyLinkedListNode currentNode = TriplyLinkedListNode.flattenList(node1_1);
		TriplyLinkedListNode currentNode = TriplyLinkedListNode.flattenListRec(node1_1);
//		TriplyLinkedListNode currentNode = node1_1;
		
		Date endDate = new Date();
		Timestamp endTimestamp = new Timestamp(endDate.getTime());
		System.out.println(endTimestamp);
		System.out.println((endTimestamp.getNanos() - startTimestamp.getNanos()));
		for(int i =0; i <= 100; i++) {
			if (currentNode == null) {
				break;
			}
			System.out.println(currentNode.getData() + " | down node:" + currentNode.getDown());
			
			// next node - right
			currentNode = currentNode.getRight();
		}
		
	}

}
