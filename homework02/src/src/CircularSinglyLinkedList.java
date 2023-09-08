package src;

import java.util.NoSuchElementException;

/**
 * Your implementation of a src.CircularSinglyLinkedList without a tail pointer.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (data == null) {
            throw new IllegalArgumentException("Can't insert null data");
        }

        CircularSinglyLinkedListNode<T> prev = null;
        CircularSinglyLinkedListNode<T> curr = head;

        int count = 0;

        while (count < index) {
            prev = curr;
            curr = curr.getNext();
            count++;
        }

        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data, curr);
        prev.setNext(newNode);

        this.size++;
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Can't insert null data");
        }

        head = this.insert(data);
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Can't insert null data");
        }

        this.insert(data);
    }

    /**
     * Inserts a node at a position
     * @param data to back or front
     * @return node - the head of the linked list
     */
    private CircularSinglyLinkedListNode<T> insert(T data) {

        if (head == null) {
            this.handleNullCase(data);
            this.size++;
            return head;
        }

        CircularSinglyLinkedListNode<T> lastNode = findLastNode();
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data, head);
        lastNode.setNext(newNode);
        this.size++;

        return newNode;
    }

    /**
     * finds node at the end of list
     * @return node - the head of the linked list
     */
    private CircularSinglyLinkedListNode<T> findLastNode() {

        CircularSinglyLinkedListNode<T> prev = head;
        CircularSinglyLinkedListNode<T> curr = head.getNext();

        while (curr != head) {
            prev = curr;
            curr = curr.getNext();
        }

        return prev;
    }

    /**
     * Inserts a node when head is null
     * @param data - data to be inserted
     */
    private void handleNullCase(T data) {
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data, null);
        newNode.setNext(newNode);
        head = newNode;
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        CircularSinglyLinkedListNode<T> prev = null;
        CircularSinglyLinkedListNode<T> curr = head;

        int count = 0;

        while (count < index) {
            prev = curr;
            curr = curr.getNext();
            count++;
        }

        this.size--;

        T data = prev.getData();

        prev.setNext(curr.getNext());

        return data;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {

        if (head == null) {
            throw new NoSuchElementException("Element doesn't exist");
        }

        T data = head.getData();
        head.setNext(head.getNext().getNext());
        this.size--;

        return data;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {

        if (head == null) {
            throw new NoSuchElementException("Element doesn't exist");
        }

        CircularSinglyLinkedListNode<T> toRemove = head;
        CircularSinglyLinkedListNode<T> curr = head.getNext();
        CircularSinglyLinkedListNode<T> prev = head;

        while (curr != head) {
            prev = toRemove;
            toRemove = curr;
            curr = curr.getNext();
        }

        T data = toRemove.getData();
        this.size--;

        prev.setNext(toRemove.getNext());

        return data;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int count = 0;

        CircularSinglyLinkedListNode<T> curr = head;

        while (count < index) {
            count++;
            curr = curr.getNext();
        }

        return curr.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.head = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }

        if (head == null) {
            throw new NoSuchElementException("Element doesn't exist");
        }

        CircularSinglyLinkedListNode<T> curr = head;
        CircularSinglyLinkedListNode<T> prev = null;
        CircularSinglyLinkedListNode<T> found = null;

        while (prev != head) {

            if (data.equals(curr.getData())) {
                found = prev;
            }

            prev = curr;
            curr = curr.getNext();
        }

        found.setNext(found.getNext().getNext());

        return data;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {

        T[] elements = (T[]) new Object[size];

        int count = 0;

        CircularSinglyLinkedListNode<T> curr = head;

        while (count < size) {
            elements[count++] = curr.getData();
            curr = curr.getNext();
        }

        return elements;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
