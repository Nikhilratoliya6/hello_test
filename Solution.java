import java.util.*;

public class Solution {
    class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
        Node(){

        }
        Node(int data){
            data=this.data;
        }
    }

    // Method to construct the tree from an array representation
    public Node constructTree(int[] arr) {
        Node root = null;
        Stack<Node> stack = new Stack<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                stack.pop();
            } else {
                Node node = new Node();
                node.data = arr[i];
                
                if (stack.size() > 0) {
                    stack.peek().children.add(node);
                } else {
                    root = node;
                }
                stack.push(node);
            }
        }
        return root;
    }
    //Level order traversal withiut uusing 2 queue--we can add null after level end 
    public void levelwise(Node node){
        Queue<Node>mq=new ArrayDeque<>() ;
        mq.add(node);
        mq.add(new Node(-1));


        while(mq.size()>0){
            if(node.data!=-1){
                node=mq.remove();
            System.out.print(node.data+" ");

            for(Node child: node.children){
                mq.add(child);

            }

            }
            else{
                if(mq.size()>0){
                    mq.add(new Node(-1));
                    System.out.println();
                }

            }
            
        }
            
        
    }

    // Method to print the tree line-wise in a zigzag manner--this can be done using the stack 
    public void linelevelwise_zigzag(Node node) {
        if (node == null) return;

        Stack<Node> ms = new Stack<>();
        ms.push(node);
        Stack<Node> cs = new Stack<>();
        int level = 1;

        while (ms.size() > 0) {
            Node currentNode = ms.pop();
            System.out.print(currentNode.data + " ");

            if (level % 2 == 1) { // when odd 
                for (int i = 0; i < currentNode.children.size(); i++) {
                    Node child = currentNode.children.get(i);
                    cs.push(child);
                }
            } else {  
                for (int i = currentNode.children.size() - 1; i >= 0; i--) {
                    Node child = currentNode.children.get(i);
                    cs.push(child);
                }
            }

            if (ms.size() == 0) {
                ms = cs;
                cs = new Stack<>();
                level++;
                System.out.println();
            }
        }
    }
  // revwerse the generic tree 
    public  static void mirrorInGenericTree(Node node){
        for(Node child:node.children){
            mirrorInGenericTree(child);

        }
        Collections.reverse(node.children);

    }

    public static void main(String[] args) {
        int arr[] = {10, 20, 50, -1, 30, 70, -1, 110, -1, 120, -1, -1, 40, -1, -1};
        Solution m = new Solution();
        Node root = m.constructTree(arr);
        m.linelevelwise_zigzag(root);
        System.out.println("levvelwise traversal is");
        m.levelwise(root);
    }
}
