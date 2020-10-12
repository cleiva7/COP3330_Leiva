public class Triangle extends Shape2D {
    String name = "triangle";
    double length;
    double height;

    public Triangle(double length, double height) {
        this.length = length;
        this.height = height;
    }

    public double getArea() {
        return length*height/2;
    }

    public String getName(){
        return name;
    }
}
