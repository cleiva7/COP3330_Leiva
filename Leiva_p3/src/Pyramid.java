public class Pyramid extends Shape3D {
    String name = "pyramid";
    double length;
    double width;
    double height;

    public Pyramid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return length*width + length*Math.sqrt(Math.pow(width/2, 2) + Math.pow(height, 2)) + width * Math.sqrt(Math.pow(length/2, 2) + Math.pow(height, 2));
    }

    public double getVolume() {
        return (length*width*height)/3;
    }

    public String getName(){
        return name;
    }
}
