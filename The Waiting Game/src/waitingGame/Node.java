package waitingGame;

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
