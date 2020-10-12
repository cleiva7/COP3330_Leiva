public class Sphere extends Shape3D {
    String name = "sphere";
    double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return 4 * Math.pow(radius, 2) * Math.PI;
    }

    public double getVolume() {
        return (4.0/3) * Math.PI * Math.pow(radius, 3);
    }

    public String getName(){
        return name;
    }
}
