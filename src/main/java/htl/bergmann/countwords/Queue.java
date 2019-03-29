
package htl.bergmann.countwords;

import java.util.LinkedList;

public class Queue <T> {
    private LinkedList<T> queue = new LinkedList<>();
    private int maxSize;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
    }
        
    public void put(T element) throws FullException {
        if(queue.size() >= maxSize) throw new FullException();
        queue.add(element);
    }
    
    public T get() throws EmptyException {
        if(queue.isEmpty()) throw new EmptyException();
        return queue.remove(0);
    }
}
