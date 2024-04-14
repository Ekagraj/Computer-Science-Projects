/*Name - Ekagra Jain
* Pledge - I pledge my honor that I have abided by the Stevens Honor System
* HW-3*/

package HW3;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class ListQueue<E> {
    private Node<E> front;
    private int size;

    public static class Node<E> {
        private E data;
        private Node<E> next;
        private int priority;

        public Node(E dataItem) {
            // This constructor creates a node holding dataItem.
            data = dataItem;
            next = null;
            priority = Integer.MAX_VALUE;
        }

        public Node(E dataItem, int priority) {
            //This constructor creates a node holding dataItem, with priority as the priority of the task
            data = dataItem;
            next = null;
            this.priority = priority;
        }

        public Node(E dataItem, Node<E> next, int priority) {
            //This constructor that creates a node holding dataItem, with next as next and priority as the priority of the task.
            data = dataItem;
            this.next = next;
            this.priority = priority;
        }

        public E getData() {
            // This function will return the data stored in the attribute.
            return data;
        }

        public Node<E> getNext() {
            // This function will return the next node.
            return next;
        }

    }
    public class Iter implements Iterator<E> {

        private Node<E> next = front;

        public boolean hasNext() {
            //This function will return true when the next Node not equal to null
            if (next == null) {
                return false;
            } else {
                return true;
            }
        }
        public E next() {
            // This function will return the data stored in the next node (attribute) and will update the next node attribute with the next node of next node
            if (hasNext() == false) {
                throw new NoSuchElementException();
            } else {
                E item = next.getData();
                next = next.next;
                return item;
            }

        }

        public void remove() {
            // This function throws UnsupportedOperationException exception.

            throw new UnsupportedOperationException();
        }

    }

    public ListQueue() {
        // This constructor creates an empty single-linked list representing the priority queue.
        front = null;
        size = 0;
    }

    public ListQueue(Node<E> first) {
        /*This Constructor creates a one-element single-linked list representing
          the priority queue. first parameter will be stored in front of the queue.*/
        front = first;
        size = 1;
    }
    public Node<E> getFront() {
        // This function is the getter function for front.
        return front;
    }

    public void setFront(Node<E> front) {
        // This function is the setter function for front.
        this.front = front;
    }

    public int getSize() {
        // This function is the getter function for size.
        return size;
    }

    public E peek() {
        // This functions returns the information at the front of the queue.
        if (front == null) {
            return null;
        } else {
            return front.data;
        }
    }
    public boolean offer(E item, int priority){
        /* This function adds item to a position according to its priority.
        If there exists same-priority tasks in the list,
        item will be added after the existing same-priority level tasks.*/
        Node<E> addnewNode = new Node<E>(item, priority);
        if(item == null){
            throw new NullPointerException();
        }
        else if(front == null){
            front = addnewNode;
        }
        else if (front.priority > addnewNode.priority) {
            addnewNode.next = front;
            front = addnewNode;

        } else {
            Node<E> current = front;
            while (current.next != null && current.next.priority <= addnewNode.priority) {
                current = current.next;
            }
            addnewNode.next = current.next;
            current.next = addnewNode;
        }
        size++;
        return true;
    }
    public boolean addRear(E item) {
        // This function adds item at the end of queue. It always returns true
        //except it throws NullPointerException if the item sent to the method is null.
        Node<E> addnewNode = new Node<E>(item);

        if (item == null) {
            throw new NullPointerException();
        }
        if (front == null) {
            front = addnewNode;
        } else {
            Node<E> current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = addnewNode;
        }
        size++;
        return true;
    }

    public E poll() {
        // This function returns the data at the front of the queue and removes it from the queue.
        if (front == null) {
            throw new NullPointerException();
        } else {
            E copy = front.data;
            front= front.next;
            size--;
            return copy;
        }

    }

    public boolean remove(Node<E> toBeRemoved){
        // This function takes a node to be removed and removes it from the queue.

        boolean isfound = false;
        if(front == null){
            isfound = false;
        }

        else if(front.data == toBeRemoved.data){
            front = front.next;
            isfound = true;
            size--;
        }

        else{
            Node<E> curr = front;
            while (curr.next != null && curr.next.getData() != toBeRemoved.data){
                curr = curr.next;
            }
            if(curr.next != null) {
                curr.next = curr.next.next;
                isfound = true;
                size--;
            }
        }
        return isfound;

    }
    public Iterator<E> iterator(){
        // This method will return an instance of Iter class that we defined inside the ListQueue<E> class.

        return new Iter();
    }

}
