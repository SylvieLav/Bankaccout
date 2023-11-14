package fr.arolla.java_new_features;

public class Main {

    public static void displayShape(Shape shape) {
        switch (shape) {
            case Circle circle -> System.out.println(circle);
            case Triangle triangle -> System.out.println(triangle);
        }
    }

    public static void main(String[] args) {
//        var server = SimpleFileServer.createFileServer(
//                new InetSocketAddress(8080),
//                Path.of("/Users/rindra/projects/kata-arolla/Bankaccout/src/main/resources"),
//                SimpleFileServer.OutputLevel.VERBOSE);
//        server.start();

        displayShape(new Circle());
        displayShape(new Triangle());
    }
}
