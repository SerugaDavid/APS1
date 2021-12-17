package APS1.Izziv5;

public class Izziv5 {
    public static void main(String[] args) {
        ArrayHeapPQ<Integer> urejen = new ArrayHeapPQ<>();
        ArrayPQ<Integer> neurejen = new ArrayPQ<>();
        LinkedHeapPQ<Integer> nodes = new LinkedHeapPQ<>();

        // testiranje LinkedHeapPQ razreda - neuspešno
        /*
        for (int i = 0; i < 15; i++) {
            nodes.enqueue(i);
            System.out.println(nodes.toString());
        }

        for (int i = 0; i < 15; i++){
            try {
                System.out.println(nodes.front().toString());
                nodes.dequeue();
                System.out.println(nodes.toString()+"\n");
            }
            catch (CollectionException e){
                System.out.println(e.toString());
            }
        }
        */

        // pravo testiranje
        // vsa koda za preverjanje LinkedHeapPQ razreda zakomentirana, ker mi nekaj ne deluje in mi ni uspelo poraviti napake
        // timer
        long casUrejen = 0, casNeurejen = 0, casNodes = 0;
        long temp;

        // polnjenje
        int nakljucno;
        for (int i = 0; i < 1000; i++){
            nakljucno = (int)(Math.random()*200);
            temp = System.nanoTime();
            urejen.enqueue(nakljucno);
            casUrejen += System.nanoTime()-temp;
            temp = System.nanoTime();
            neurejen.enqueue(nakljucno);
            casNeurejen += System.nanoTime()-temp;
            temp = System.nanoTime();
            //nodes.enqueue(nakljucno);
            casNodes += System.nanoTime()-temp;
        }

        // front, deque, enque
        for (int i = 0; i < 500; i++){
            nakljucno = (int)(Math.random()*200);
            // neurejen
            temp = System.nanoTime();
            try {
                neurejen.dequeue();
                neurejen.enqueue(nakljucno);
                neurejen.front();
            } catch (CollectionException e){
                System.out.println("Neurejen ERR");
            }
            casNeurejen += System.nanoTime()-temp;

            // urejen
            temp = System.nanoTime();
            try {
                urejen.dequeue();
                urejen.enqueue(nakljucno);
                urejen.front();
            } catch (CollectionException e){
                System.out.println("Urejen ERR");
            }
            casUrejen += System.nanoTime()-temp;

            // nodes
            temp = System.nanoTime();
            /*try {
                nodes.dequeue();
                nodes.enqueue(nakljucno);
                nodes.front();
            } catch (CollectionException e){
                System.out.println("Nodes ERR");
            }*/
            casNodes += System.nanoTime()-temp;
        }

        // stats
        int[] statsUrejen = urejen.stats();
        int[] statsNeurejen = neurejen.stats();
        int[] statsNodes = nodes.stats();

        // Izpis
        System.out.println("Objekti: Integer\n");
        System.out.println("Operacije: 1000 enqueue + 500 (dequeue+enqueue+front)\n");
        System.out.println("Implementacija                     Čas [ms]           Premikov             Primerjav\n");
        System.out.println("--------------------------------------------------------------------------------------\n");
        System.out.printf("Neurejeno poje%20s %-18s %-20s %d\n", "", casNeurejen/1000, statsNeurejen[0], statsNeurejen[1]);
        System.out.printf("Implicitna kopica%17s %-18s %-20s %d\n", "", casUrejen/1000, statsUrejen[0], statsUrejen[1]);
        System.out.printf("Eksplicitna kopica%16s %-18s %-20s %d\n", "", casNodes/1000, statsNodes[0], statsNodes[1]);
    }
}
