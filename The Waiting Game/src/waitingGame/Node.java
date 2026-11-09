package project03cs2114;

class Node<E>
{
    final E data;
    Node<E> next;
    Node<E> previous;

    Node(E data)
    {
        this.data = data;
    }
}
