package ilyin.java.homework1;




public class Main {
    public static void main(String[] args) {

        HashMapImpl map = new HashMapImpl<>();

        map.put(1, "Ivan");
        map.put(2,"Andrey");

        map.outPut();


        map.remove(1);

        System.out.println(map.get(2));

    }
}