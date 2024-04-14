package HW4;

import java.util.Random;
import java.util.Stack;
public class Treap<E extends Comparable<E>> {

    private static class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority) {
            if (data == null) {
                throw new IllegalArgumentException();
            }
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }
        public Node<E> rotateRight() {
            if (this.left == null) {
                return this;
            }
            Node<E> temp = this.left;
            this.left = temp.right;
            temp.right = this;
            return temp;
        }
        public Node<E> rotateLeft() {
            if (this.right == null) {
                return this;
            }
            Node<E> temp = this.right;
            this.right = temp.left;
            temp.left = this;
            return temp;
        }
    }
    private Random priorityGenerator;
    private Node<E> root;

    // Getter functions.
    public Node<E> getRoot(){
        return root;
    }

    public Random getPriorityGenerator() {
        return priorityGenerator;
    }
    public Treap() {
        root = null;
        priorityGenerator = new Random();
    }


    public Treap(long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }
    public boolean add(E key) {
        int priority = priorityGenerator.nextInt();
        return add(key, priority);
    }

    public boolean add(E key, int priority) {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> newNode = new Node<>(key, priority);
        Node<E> currentNode = root;

        if(key == null){
            return false;
        }

        if (root == null) {
            root = newNode;
            return true;
        }
        stack.push(currentNode);
        while (true) {
            int compareNode = key.compareTo(currentNode.data);
            if (compareNode < 0) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    break;
                }
                currentNode = currentNode.left;
                stack.push(currentNode);

            } else if (compareNode > 0) {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    break;
                }
                currentNode = currentNode.right;
                stack.push(currentNode);

            } else {
                return false;
            }
        }
        reheap(stack, newNode);
        return true;
    }
    private void reheap(Stack<Node<E>> stack, Node<E> newNode) {
        if (newNode == null) {
            return;
        }
        while (!stack.isEmpty()) {
            Node<E> parent = stack.pop();
            if (parent.priority < newNode.priority) {
                if (parent.data.compareTo(newNode.data) > 0) {
                    newNode = parent.rotateRight();
                } else {
                    newNode = parent.rotateLeft();
                }
                if (!stack.isEmpty()) {
                    if (stack.peek().left == parent) {
                        stack.peek().left = newNode;
                    } else {
                        stack.peek().right = newNode;
                    }
                } else {
                    this.root = newNode;
                }
            } else {
                break;
            }
        }
    }

    public boolean delete(E key) {
        if (key == null) {
            return false;
        }
        if(!find(key)){
            return false;
        }
        root = delete(root, key);
        return true;
    }

    private Node<E> delete(Node<E> root, E key) {
        if (root == null) {
            return null;
        }
        if (key == null) {
            throw new IllegalArgumentException("Key to be deleted is null!");
        }
        int compareNode = key.compareTo(root.data);

        if (compareNode < 0) {
            root.left = delete(root.left, key);
        } else if (compareNode > 0) {
            root.right = delete(root.right, key);
        } else {
            if(root.left == null && root.right == null){
                return null;
            }
            if (root.left == null && root.right != null) {
                return root.right;

            } else if (root.right == null && root.left != null) {
                return root.left;

            } else {
                if (root.left.priority > root.right.priority) {
                    root = root.rotateRight();
                    return delete(root, key);
                }
                if (root.left.priority < root.right.priority) {
                    root = root.rotateLeft();
                    return delete(root, key);
                }
            }

            Node temp = root;
            root = min(root.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
        }
        return root;
    }
    public Node min(Node x) {
        if (x.left  == null) return x;
        else return min(x.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public boolean find(E key) {
        return find(root, key);
    }

    private boolean find(Node<E> root, E key) {
        if (key == null)
            return false;
        if (root != null && key.compareTo(root.data) == 0)
            return true;
        while (root != null) {
            int compareNode = key.compareTo(root.data);
            if (compareNode < 0) {
                root = root.left;
            } else if (compareNode > 0) {
                root = root.right;
            } else {
                return true;
            }
        }
        return false;
    }

    private String toString(Node<E> currentNode, int height) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < height; i++)
            s.append("  ");
        if (currentNode == null)
            s.append("null\n");
        else {
            s.append("(key=" + currentNode.data.toString() + ", priority=" + currentNode.priority + ")" + "\n");
            s.append(toString(currentNode.left, height + 1));
            s.append(toString(currentNode.right, height + 1));
        }
        return s.toString();
    }

    public String toString() {
        return toString(root, 0);
    }
}





