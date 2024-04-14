/*Name - Ekagra Jain
 * Pledge - I pledge my honor that I have abided by the Stevens Honor System
 * HW-3*/

package HW3;

import java.util.Iterator;

public class TaskList<E>{
    private ListQueue<E> all;
    private ListQueue<E> active;
    private ListQueue<E> completed;

    private static final int LOW_PRIORITY = Integer.MAX_VALUE;

    private static final int HIGH_PRIORITY = 1;

    public TaskList(){
        // This constructor initializes all the ListQueue attributes (all, active, completed).
        all = new ListQueue<E>();
        active = new ListQueue<E>();
        completed = new ListQueue<E>();
    }
    public boolean createTask(E item){
        /* This function add the item into active and all queues with default priority
        as LOW_PRIORITY. If item is null, it will return false. Otherwise returns true.*/
        if(item==null){
            return false;
        }
        else{
            all.offer(item, LOW_PRIORITY);
            active.offer(item, LOW_PRIORITY);
            return true;
        }
    }

    public boolean createTask(E item, int priority){
        // This function add the item into active and all queues. If item is null, it will return false. Otherwise returns true.
        if(item==null){
            return false;
        }
        else{
            all.offer(item, priority);
            active.offer(item, priority);
            return true;
        }
    }
    public ListQueue<E> getActive() {
        // This function is the getter for Active
        return active;
    }

    public ListQueue<E> getAll() {
        //// This function is the getter for All
        return all;
    }

    public ListQueue<E> getCompleted() {
        // This function is the getter for Completed
        return completed;
    }
    public String getTopThreeTasks() {
        // This function returns a string that has the top 3 highest priority tasks.
        StringBuilder tasksString = new StringBuilder();
        Iterator<E> it = active.iterator();
        int count = 0;
        while (it.hasNext() && count < 3) {
            tasksString.append(it.next()).append("\n");
            count++;
        }
        return tasksString.toString();
    }

    private void printTasks(ListQueue<E> queue){
        /*This function acts as a helper method which will use the iterator() to iterate through
        the queue elements and print them with numbers. Front of the queue will have the task
        number 1 and each next node will have an increasing task number.*/
        Iterator<E> it= queue.iterator();
        int i=1;
        while (it.hasNext()){
            System.out.println(i + ". " + it.next());
            i++;
        }
    }
    public void showActiveTasks(){
        /*This function calls the helper method printTasks(ListQueue<E> queue),
        queue is indicating the respective list we sent to this helper method*/
        printTasks(active);
    }
    public void showAllTasks(){
        /*This function calls the helper method printTasks(ListQueue<E> queue),
        queue is indicating the respective list we sent to this helper method*/
        printTasks(all);
    }
    public void showCompletedTasks(){
        /*This function calls the helper method printTasks(ListQueue<E> queue),
        queue is indicating the respective list we sent to this helper method*/
        printTasks(completed);
    }
    public boolean crossOffMostUrgent(){
        // This function will remove the highest priority task from the front of the
        // queue and returns true if it successfully removes. The task will be added to the completed queue.
        // If it does not exist, it prints an error message and returns false.
        if(active.getFront()==null){
            return false;
        }
        else{
            E item = active.peek();
            active.poll();
            completed.addRear(item);
            return true;
        }
    }
    public boolean crossOffTask(int taskNumber) {
        // This function removes the task at the location identified by the
        //taskNumber.
        if (active.getFront() == null || taskNumber > active.getSize()) {
            return false;
        } else {
            ListQueue.Node<E> removed = active.getFront();
            for (int i = 1; i < taskNumber; i++) {
                removed = removed.getNext();
            }
            E removedTask = removed.getData();
            active.remove(removed);
            completed.addRear(removedTask);
            return true;
        }
    }

}

