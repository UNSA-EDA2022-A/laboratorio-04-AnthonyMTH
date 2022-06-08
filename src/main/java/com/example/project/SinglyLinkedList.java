package com.example.project;

public class SinglyLinkedList<T> {
    private Node<T> first; // Primero nodo de la lista
    private int size; // Tamano de la lista

    // Constructor (crea lista vacia)
    SinglyLinkedList() {
        first = null;
        size = 0;
    }

    // Retorna el tamano de la lista
    public int size() {
        return size;
    }

    // Devuelve true si la lista esta vazia o false caso contrario
    public boolean isEmpty() {
        return (size == 0);
    }

    // Adiciona v al inicio de la lista
    public void addFirst(T v) {
        Node<T> newNode = new Node<T>(v, first);
        first = newNode;
        size++;
    }

    // Adiciona v al final de la lista
    public void addLast(T v) {
        Node<T> newNode = new Node<T>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> cur = first;
            while (cur.getNext() != null)
                cur = cur.getNext();
            cur.setNext(newNode);
        }
        size++;
    }

    // Retorna el primer valor de la lista (o null si la lista esta vacia)
    public T getFirst() {
        if (isEmpty())
            return null;
        return first.getValue();
    }

    // Retorna el ultimo valor de la lista (o null si la lista esta vazia)
    public T getLast() {
        if (isEmpty())
            return null;
        Node<T> cur = first;
        while (cur.getNext() != null)
            cur = cur.getNext();
        return cur.getValue();
    }

    // Elimina el primer elemento de la lista (si esta vacia no hara nada)
    public void removeFirst() {
        if (isEmpty())
            return;
        first = first.getNext();
        size--;
    }

    // Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
    public void removeLast() {
        if (isEmpty())
            return;
        if (size == 1) {
            first = null;
        } else {
            // Ciclo con for y uso de size para mostrar alternativa al while
            Node<T> cur = first;
            for (int i = 0; i < size - 2; i++)
                cur = cur.getNext();
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    // Convierte la lista para um String
    public String toString() {
        String str = "{";
        Node<T> cur = first;
        while (cur != null) {
            str += cur.getValue();
            cur = cur.getNext();
            if (cur != null)
                str += ",";
        }
        str += "}";
        return str;
    }

    // NUEVOS METODOS

    // Elimina aquellos nodos de la lista que esten duplicados
    public void deleteDuplicates() {
        Node<T> aux = this.first;
        Node<T> temp = aux.getNext(); //temp va a ser quien se mueva para buscar nodos repetidos y eliminarlos
        for(int j = 1; aux.getNext() != null; aux = aux.getNext(), temp = aux.getNext(), j++){
            //Este for recorrerá hasta llegar al penúltimo nodo, "j" nos servirá para localizar el índice
            for(int i = j; temp != null;) {
                //Este for se inicia con i = j, en donde i representa el índice que se está comparando con aux
                if(temp.compareTo(aux) == 0) { //Si son iguales se elimina
                    temp = temp.getNext(); 
                    deleteNth(i); //Eliminar
                    continue; //Para no cambiar el valor de i
                }
                //En caso no sean iguales el valor de i(índice) aumenta y se sigue el bucle hasta que "temp" sea null
                temp = temp.getNext();
                i++;
            }  
        }
    }

    // Inserta un nuevo nodo en una posicion especifica de la lista
    public void insertNth(T data, int position) {
        if(isEmpty() || position > size){
            if(isEmpty() && position == 0) //Inserta primer elemento
                addFirst(data);
            else
                System.out.println("Fuera de rango.");
        }  
        else if(position >= 0){
            //Verifica que la posición que se quiera eliminar sea un valor válido
            if(position == 0)
                //Si nos pide insertar en la posición 0
                addFirst(data);
            else { 
                Node<T> aux = this.first;
                for (int j = 0; j < position-1; j++) 
                    //Recorre hasta la posición-1, para asignar la posición siguiente al nodo que salga del bucle
                    aux = aux.getNext();
                //Se inserta el nuevo nodo
                aux.setNext(new Node<T>(data, aux.getNext()));
                size++;
            }
        }
    }

    // Elimina el nodo de una posicion especifica de la lista
    public void deleteNth(int position) {
        if(isEmpty() || position > size){
            if(isEmpty())
                //Si está vacío, envía un mensaje 
                System.out.println("Vacío"); 
            else
                System.out.println("Fuera de rango.");
        }       
        else if(position >= 0){
            //Verifica que la posición que se quiera eliminar sea un valor válido
            if(position == 0)
                //Si nos pide eliminar la posición 0
                removeFirst();
            else {
                Node<T> aux = this.first;
                for (int i = 0; i < position-1; i++) 
                    //Recorre hasta la posición-1, para "setearlo" al subsiguiente de ese mismo (eliminar)
                    aux = aux.getNext();
                aux.setNext(aux.getNext().getNext()); //Elimina
                size--;
            }
        }
    }

    public static void main(final String[] args) {

        testExercicio1();
        testExercicio2();
        testExercicio3();       

    }

    public static void testExercicio1(){

        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        list.addLast(47);
        list.addLast(89);
        list.addLast(56);
        list.addLast(89);
        list.addLast(56);

        System.out.println(list);

        list.deleteDuplicates();

        System.out.println(list);
    }

    public static void testExercicio2(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.insertNth('c', 2);

        System.out.println(list);
    }

    public static void testExercicio3(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.deleteNth(2);

        System.out.println(list);
    }

}
