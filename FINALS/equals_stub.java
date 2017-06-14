public class Thing {
    int x;
    int y;
    int idontcare;
    
    Thing(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.idontcare = z;
    }
    
    @Override
    public equals(Thing other) {
        return this.x == other.x && this.y == other.y
    }
    
}

public static void main() {
    Thing A = new Thing(1, 2, 3);
    Thing B = new Thing(1, 2, 3);
    
    A.equals(A) //true
    A.equals(B) //false
    
    //After overrriding
    
    A.equals(B) //true
    
}