public class Cube extends Shape3D {
    String name = "cube";
    double sideLength;

    public Cube(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getArea() {
        return Math.pow(sideLength, 2) * 6;
    }

    public double getVolume() {
        return Math.pow(sideLength, 3);
    }

    public String getName(){
        return name;
    }
}
