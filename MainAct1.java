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
public class MainAct1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("===== LINKED LIST DEMO =====");
        System.out.println("1. Integer Example");
        System.out.println("2. String Example");
        System.out.println("3. Contact Example");
        System.out.println("4. Custom List");
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
        } else {
            System.out.println("Invalid option.");
        }
        System.out.println("Diego Villarreal Al07064821");
    }
}

