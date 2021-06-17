package homework;

public class SerialWork {
    

    public static void main(String[] args) {
        Heap heap = new Heap(100);
        Truck truck = new Truck(6,0); //тележка вмещает 6 юнитов и изначально пустая
        WorkProcess workProcess = new WorkProcess(false,true,true,"loader"); //загрузчик разблокирован, перевозчик и разгрузчик заблокированы
        new Loader("loader", 3, 1000, 8000, heap, truck, workProcess); //скорость = 3 ед. за 1000 мс
        new Transporter("transporter", 5000, workProcess); //время провоза = 5000 мс
        new Unloader("unloader", 2, 1000, truck, workProcess); //скорость = 2 ед. за 1000 мс
    }

}