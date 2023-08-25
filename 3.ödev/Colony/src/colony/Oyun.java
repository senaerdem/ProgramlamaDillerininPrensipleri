/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
* Oyunun yönetildiği ve koloniler arasındaki savaşların gerçekleştiği sınıf
* </p>
*/
package colony;
import java.util.Random;

class Oyun {
    private Koloni[] koloniler;
    private int turSayisi;
    private static final int SLEEP_TIME = 2000; // Her tur arasındaki bekleme süresi (milisaniye cinsinden)
    private static final String CLEAR_SCREEN_COMMAND = "\033[H\033[2J"; // Ekranı temizlemek için kullanılan komut

    public Oyun(Koloni[] koloniler) {
        this.koloniler = koloniler;
        this.turSayisi = 0;
    }

    public void baslat() {
        Random rand = new Random();
        int kalanKoloniSayisi = koloniler.length;

        while (kalanKoloniSayisi > 1) {
            // Her koloni için popülasyonu %20 artır ve yemek stoğunu (GüncelPopülasyon x 2) azalt
            // Savaşları gerçekleştir ve sonuçlara göre popülasyon ve yemek stoğu değiştir
            // Yemek stoğu veya popülasyonu sıfıra veya eksiye düşen kolonilerin yaşamını sonlandır

            // Mevcut turun sonuçlarını ekrana yazdırma
        	try {
                Thread.sleep(SLEEP_TIME);
                System.out.print(CLEAR_SCREEN_COMMAND);
                System.out.flush();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        	System.out.println("-------------------------------------------------------------");
            System.out.println("Tur Sayısı: " + turSayisi);
            System.out.println("Koloni\tPopülasyon\tYemek Stoğu\tKazanma\tKaybetme");

            for (Koloni koloni : koloniler) {
                if (koloni.hayattaMi()) {
                    System.out.println(koloni.getSembol() + "\t" + koloni.getPopulasyon() + "\t\t" + koloni.getYemekStogu() + "\t\t"
                            + koloni.getKazanma() + "\t" + koloni.getKaybetme());
                    koloni.artirPopulasyon();
                    koloni.azaltYemekStogu();
                    int uretilenYemek = koloni.uretYemek();
                    // System.out.println("uretilen yemek: " + uretilenYemek);
                    koloni.yemekStogu += uretilenYemek;
                } else {
                    System.out.println(koloni.getSembol() + "\t—\t\t—\t\t—\t—");
                }
            }

            System.out.println("-------------------------------------------------------------");
            turSayisi++;

            // Savaşları gerçekleştir ve sonuçlara göre popülasyon ve yemek stoğu değiştir
            for (int i = 0; i < koloniler.length; i++) {
                Koloni koloni1 = koloniler[i];
                if (!koloni1.hayattaMi()) {
                    continue;
                }

                for (int j = i + 1; j < koloniler.length; j++) {
                    Koloni koloni2 = koloniler[j];
                    if (!koloni2.hayattaMi()) {
                        continue;
                    }
                    koloni1.savas(koloni2);
                }
            }

            // Yemek stoğu veya popülasyonu sıfıra veya eksiye düşen kolonilerin yaşamını sonlandır ve kalan koloni sayısını güncelle
            kalanKoloniSayisi = 0;
            for (Koloni koloni : koloniler) {
                if (koloni.hayattaMi()) {
                    kalanKoloniSayisi++;
                }
            }
        }

        // Sadece 1 koloni kaldığında
        try {
            Thread.sleep(SLEEP_TIME);
            System.out.print(CLEAR_SCREEN_COMMAND);
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Tur Sayısı: " + turSayisi);
        System.out.println("Koloni\tPopülasyon\tYemek Stoğu\tKazanma\tKaybetme");
        for (Koloni koloni : koloniler) {
            if (koloni.hayattaMi()) {
                System.out.println(koloni.getSembol() + "\t" + koloni.getPopulasyon() + "\t\t" + koloni.getYemekStogu() + "\t\t"
                        + koloni.getKazanma() + "\t" + koloni.getKaybetme());
            } else {
                System.out.println(koloni.getSembol() + "\t—\t\t—\t\t—\t—");
            }
        }
        System.out.println("-------------------------------------------------------------");
    }
}