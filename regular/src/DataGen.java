public class DataGen<T> {
    private T data;
    private int lineNumber;

    public DataGen() {
    }

    public DataGen(T data,int lineNumber) {
        this.data = data;
        this.lineNumber = lineNumber;
    }

    public String toString() {
        return String.format("%-15s at line %5d",data,lineNumber);
    }

    public T getData() {
        return data;
    }
}
