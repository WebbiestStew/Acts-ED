import java.io.*; 

// ====================== Node Class ======================
class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev; 

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}

// ====================== LinkedList Class ======================
class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private boolean isCircular;
    private boolean isDoubly;

    public LinkedList(boolean isCircular, boolean isDoubly) {
        this.head = null;
        this.tail = null;
        this.isCircular = isCircular;
        this.isDoubly = isDoubly;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
            if (isCircular) tail.setNext(head);
        } else {
            tail.setNext(newNode);
            if (isDoubly) newNode.setPrev(tail);
            tail = newNode;
            if (isCircular) tail.setNext(head);
        }
    }

    public void addAtFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
            if (isCircular) tail.setNext(head);
        } else {
            newNode.setNext(head);
            if (isDoubly) head.setPrev(newNode);
            head = newNode;
            if (isCircular) tail.setNext(head);
        }
    }

    public void deleteByData(T data) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<T> current = head;
        Node<T> previous = null;

        do {
            if (current.getData().equals(data)) {
                if (previous == null) {
                    head = head.getNext();
                    if (isCircular) tail.setNext(head);
                } else {
                    previous.setNext(current.getNext());
                    if (isDoubly && current.getNext() != null)
                        current.getNext().setPrev(previous);
                    if (current == tail) tail = previous;
                }
                System.out.println("Deleted: " + data);
                return;
            }
            previous = current;
            current = current.getNext();
        } while (isCircular && current != head);

        System.out.println("Data not found.");
    }

    public boolean search(T data) {
        if (head == null) return false;
        Node<T> current = head;
        do {
            if (current.getData().equals(data)) return true;
            current = current.getNext();
        } while (isCircular && current != head);
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<T> current = head;
        System.out.print("List elements: ");
        do {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        } while (isCircular ? current != head : current != null);
        System.out.println();
    }

    public T removeFirst() {
        if (head == null) return null;
        T data = head.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            if (isDoubly && head != null) head.setPrev(null);
            if (isCircular && tail != null) tail.setNext(head);
        }
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T getFirst() {
        if (head == null) return null;
        return head.getData();
    }
}

// ====================== Stack Class ======================
class Stack<T> {
    private LinkedList<T> list;

    public Stack() {
        list = new LinkedList<>(false, true);
    }

    public void push(T data) {
        list.addAtFirst(data);
    }

    public T pop() {
        if (list.isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return list.removeFirst();
    }

    public T peek() {
        if (list.isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return list.getFirst();
    }

    public void display() {
        list.display();
    }
}

// ====================== Queue Class ======================
class Queue<T> {
    private LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>(false, true);
    }

    public void enqueue(T data) {
        list.add(data);
    }

    public T dequeue() {
        if (list.isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return list.removeFirst();
    }

    public T peek() {
        if (list.isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return list.getFirst();
    }

    public void display() {
        list.display();
    }
}

// ====================== Contact Class ======================
class Contact {
    private String name;
    private String address;
    private String phone;

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String toString() {
        return "[" + name + " | " + address + " | " + phone + "]";
    }
}

// ====================== DataTypeExamples Class ======================
class DataTypeExamples {
    public static void showIntegerExample() {
        LinkedList<Integer> list = new LinkedList<>(false, false);
        list.add(10);
        list.add(20);
        list.add(30);
        list.display();
    }

    public static void showStringExample() {
        LinkedList<String> list = new LinkedList<>(false, false);
        list.add("Alpha");
        list.add("Beta");
        list.add("Gamma");
        list.display();
    }

    public static void showContactExample() {
        LinkedList<Contact> list = new LinkedList<>(false, true);
        list.add(new Contact("Diego Villarreal", "5th Ave", "651-8020"));
        list.add(new Contact("Hector Leal", "Sunset St", "656-9128"));
        list.add(new Contact("Charlie", "Moon Blvd", "555-3333"));
        list.display();
    }
}

// ====================== Main Class ======================
public class MainAct2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("===== LINKED LIST DEMO =====");
        System.out.println("Student: Diego Villarreal Al07064821");
        System.out.println("1. Integer Example");
        System.out.println("2. String Example");
        System.out.println("3. Contact Example");
        System.out.println("4. Custom List");
        System.out.println("5. Stack and Queue");
        System.out.print("Choose an option: ");

        int choice = Integer.parseInt(reader.readLine());

        if (choice == 1) {
            DataTypeExamples.showIntegerExample();
        } else if (choice == 2) {
            DataTypeExamples.showStringExample();
        } else if (choice == 3) {
            DataTypeExamples.showContactExample();
        } else if (choice == 4) {
            System.out.print("Enter list type (singly / doubly / circular): ");
            String type = reader.readLine().toLowerCase();

            boolean isDoubly = type.equals("doubly");
            boolean isCircular = type.equals("circular");
            LinkedList<String> list = new LinkedList<>(isCircular, isDoubly);

            int option = -1;
            while (option != 0) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add element");
                System.out.println("2. Add at first");
                System.out.println("3. Delete element");
                System.out.println("4. Search element");
                System.out.println("5. Show list");
                System.out.println("0. Exit");
                System.out.print("Option: ");

                option = Integer.parseInt(reader.readLine());

                if (option == 1) {
                    System.out.print("Enter value: ");
                    String val = reader.readLine();
                    list.add(val);
                } else if (option == 2) {
                    System.out.print("Enter value: ");
                    String val = reader.readLine();
                    list.addAtFirst(val);
                } else if (option == 3) {
                    System.out.print("Enter value to delete: ");
                    String val = reader.readLine();
                    list.deleteByData(val);
                } else if (option == 4) {
                    System.out.print("Enter value to search: ");
                    String val = reader.readLine();
                    boolean found = list.search(val);
                    System.out.println(found ? "Found." : "Not found.");
                } else if (option == 5) {
                    list.display();
                } else if (option == 0) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Invalid option.");
                }
            }
        } else if (choice == 5) {
            System.out.println("Choose data structure:");
            System.out.println("1. Stack");
            System.out.println("2. Queue");
            System.out.print("Option: ");
            int dsChoice = Integer.parseInt(reader.readLine());

            if (dsChoice == 1) {
                Stack<String> stack = new Stack<>();
                int option = -1;
                while (option != 0) {
                    System.out.println("\n--- Stack Menu ---");
                    System.out.println("1. Push");
                    System.out.println("2. Pop");
                    System.out.println("3. Peek");
                    System.out.println("4. Display");
                    System.out.println("0. Exit");
                    System.out.print("Option: ");
                    option = Integer.parseInt(reader.readLine());

                    if (option == 1) {
                        System.out.print("Enter value to push: ");
                        String val = reader.readLine();
                        stack.push(val);
                    } else if (option == 2) {
                        String popped = stack.pop();
                        if (popped != null) System.out.println("Popped: " + popped);
                    } else if (option == 3) {
                        String top = stack.peek();
                        if (top != null) System.out.println("Top: " + top);
                    } else if (option == 4) {
                        stack.display();
                    } else if (option == 0) {
                        System.out.println("Exiting Stack...");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else if (dsChoice == 2) {
                Queue<String> queue = new Queue<>();
                int option = -1;
                while (option != 0) {
                    System.out.println("\n--- Queue Menu ---");
                    System.out.println("1. Enqueue");
                    System.out.println("2. Dequeue");
                    System.out.println("3. Peek");
                    System.out.println("4. Display");
                    System.out.println("0. Exit");
                    System.out.print("Option: ");
                    option = Integer.parseInt(reader.readLine());

                    if (option == 1) {
                        System.out.print("Enter value to enqueue: ");
                        String val = reader.readLine();
                        queue.enqueue(val);
                    } else if (option == 2) {
                        String dequeued = queue.dequeue();
                        if (dequeued != null) System.out.println("Dequeued: " + dequeued);
                    } else if (option == 3) {
                        String front = queue.peek();
                        if (front != null) System.out.println("Front: " + front);
                    } else if (option == 4) {
                        queue.display();
                    } else if (option == 0) {
                        System.out.println("Exiting Queue...");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            } else {
                System.out.println("Invalid option.");
            }
        } else {
            System.out.println("Invalid option.");
        }
        System.out.println("Diego Villarreal Al07064821");
    }
}
