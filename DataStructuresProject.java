// ----------------------------- NODE -----------------------------
class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
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
}

// ----------------------------- STACK -----------------------------
class Stack<T> {
    private Node<T> top;

    public Stack() {
        top = null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return null;
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public T peek() {
        return (top != null) ? top.getData() : null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void showAll() {
        Node<T> current = top;
        System.out.println("Urgent Tasks (Stack):");
        while (current != null) {
            System.out.println("- " + current.getData());
            current = current.getNext();
        }
    }
}

// ----------------------------- QUEUE -----------------------------
class Queue<T> {
    private Node<T> front, rear;

    public Queue() {
        front = rear = null;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.setNext(newNode);
        rear = newNode;
    }

    public T dequeue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = front.getData();
        front = front.getNext();
        if (front == null) rear = null;
        return data;
    }

    public T front() {
        return (front != null) ? front.getData() : null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void showAll() {
        Node<T> current = front;
        System.out.println("Scheduled Tasks (Queue):");
        while (current != null) {
            System.out.println("- " + current.getData());
            current = current.getNext();
        }
    }
}

// ----------------------------- LIST -----------------------------
class List<T> {
    private Node<T> head;

    public List() {
        head = null;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    public void delete(T data) {
        if (head == null) return;

        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }

        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public Node<T> find(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void showAll() {
        Node<T> current = head;
        System.out.println("Department Tasks (List):");
        while (current != null) {
            System.out.println("- " + current.getData());
            current = current.getNext();
        }
    }
}

// ----------------------------- MAIN PROGRAM -----------------------------
public class DataStructuresProject {

    public static void main(String[] args) throws java.io.IOException {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new Queue<>();
        List<String> list = new List<>();

        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int option;

        System.out.println("=======================================");
        System.out.println("Data Structures Project");
        System.out.println("Program by Diego Villarreal");
        System.out.println("=======================================\n");

        do {
            System.out.println("=== TASK MANAGEMENT SYSTEM ===");
            System.out.println("1. Add urgent task (Stack)");
            System.out.println("2. Add scheduled task (Queue)");
            System.out.println("3. Add department task (List)");
            System.out.println("4. View all tasks");
            System.out.println("5. Remove task from stack/queue/list");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            option = Integer.parseInt(reader.readLine());
            switch (option) {
                case 1:
                    System.out.print("Enter the urgent task: ");
                    stack.push(reader.readLine());
                    break;
                case 2:
                    System.out.print("Enter the scheduled task: ");
                    queue.enqueue(reader.readLine());
                    break;
                case 3:
                    System.out.print("Enter the department task: ");
                    list.insert(reader.readLine());
                    break;
                case 4:
                    stack.showAll();
                    queue.showAll();
                    list.showAll();
                    break;
                case 5:
                    System.out.print("From which structure do you want to remove? (stack/queue/list): ");
                    String type = reader.readLine();
                    System.out.print("Enter the task name: ");
                    String task = reader.readLine();

                    if (type.equalsIgnoreCase("stack")) stack.pop();
                    else if (type.equalsIgnoreCase("queue")) queue.dequeue();
                    else if (type.equalsIgnoreCase("list")) list.delete(task);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);
    }
}