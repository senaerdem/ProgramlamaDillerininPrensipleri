/**
* 
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
* Kolonilerin oluşturulmasını ve oyunun başlatılmasını sağlayan sınıf. Main metodu içerir.
* </p>
*/
package colony;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java. util. Scanner;

public class Test {
    private static int[] populasyonlariAl() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim(); // Başındaki ve sonundaki boşlukları temizle

        String[] tokens = input.split("\\s+"); // Birden fazla boşluk kullanımını dikkate alarak ayır
        int[] populasyonlar = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            populasyonlar[i] = Integer.parseInt(tokens[i]);
        }

        return populasyonlar;
    }

    private static char rastgeleSembolUret(List<Character> kullanilanSemboller) {
        char[] semboller = {'!', '#', '$', '%', '&', '*', '+', '?', '@', '√', '~', '☂', '/', '©', '§' ,'♣', '£', '♥', '∞', '☻', '卍', '∆', 'Ω', '★', '⇧', 'Σ', '☾', '♦','π', '❖', '►', '₺', '■','ღ'};

        if (kullanilanSemboller.size() == semboller.length) {
            // Tüm semboller kullanıldıysa, listeyi sıfırla
            kullanilanSemboller.clear();
        }

        char secilenSembol;

        do {
            secilenSembol = semboller[new Random().nextInt(semboller.length)];
        } while (kullanilanSemboller.contains(secilenSembol));

        kullanilanSemboller.add(secilenSembol);
        return secilenSembol;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Popülasyonları girin (boşlukla ayrılmış şekilde): ");
        int[] populasyonlar = populasyonlariAl();

        List<Character> kullanilanSemboller = new ArrayList<>(); // Kullanılan sembollerin tutulduğu liste
        Koloni[] koloniler = new Koloni[populasyonlar.length];
                
        for (int i = 0; i < populasyonlar.length; i++) {
            char sembol = rastgeleSembolUret(kullanilanSemboller);
            int populasyon = populasyonlar[i];

            Taktik taktik;
            if (new Random().nextInt(2) == 0) {
                taktik = new ATaktik();
            } else {
                taktik = new BTaktik();
            }

            Uretim uretim;
            if (new Random().nextInt(2) == 0) {
                uretim = new AUretim();
            } else {
                uretim = new BUretim();
            }

            koloniler[i] = new Koloni(populasyon, sembol, taktik, uretim);
        }

        Oyun oyun = new Oyun(koloniler);
        oyun.baslat();

        scanner.close();
    }
}