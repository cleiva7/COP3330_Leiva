public class Square extends Shape2D{
    String name = "square";
    double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getArea() {
        return Math.pow(sideLength, 2);
    }

    public String getName(){
        return name;
    }
}