/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
* Kolonilerin özelliklerini ve davranışlarını temsil eden sınıf
* </p>
*/
package colony;

public class Koloni {
    private int kazanma;
    private int kaybetme;
    private int yasayan;
    private int populasyon;
    public int yemekStogu;
    private char sembol;
    private Taktik taktik;
    private Uretim uretim;

    public Koloni(int populasyon, char sembol, Taktik taktik, Uretim uretim) {
        this.populasyon = populasyon;
        this.yemekStogu = populasyon * populasyon;
        this.sembol = sembol;
        this.taktik = taktik;
        this.uretim = uretim;
        this.kazanma = 0;
        this.kaybetme = 0;
        this.yasayan = populasyon;
    }

    public int getPopulasyon() {
        return populasyon;
    }

    public int getYemekStogu() {
        return yemekStogu;
    }

    public int getKazanma() {
        return kazanma;
    }

    public int getKaybetme() {
        return kaybetme;
    }

    public char getSembol() {
        return sembol;
    }

    public void artirPopulasyon() {
        populasyon = (int) (populasyon * 1.2);
    }

    public void azaltYemekStogu() {
        yemekStogu -= populasyon * 2;
    }

    public int uretYemek() {
        return uretim.uret();
    }

    public void savas(Koloni digerKoloni) {
        int savasSonucu1 = taktik.savas();
        int savasSonucu2 = digerKoloni.taktik.savas();
        
        // System.out.println("Savaş başladı " + this.sembol + "-" + digerKoloni.getSembol());
        // System.out.println(this.sembol + " kolonisi, " + savasSonucu1 + " taktik değeri üretti");
        // System.out.println(digerKoloni.getSembol() + " kolonisi, " + savasSonucu2 + " taktik değeri üretti");
        
        if (savasSonucu1 > savasSonucu2 || (savasSonucu1 == savasSonucu2 && populasyon > digerKoloni.populasyon)) {
            // Koloni1 kazandı
            int fark = savasSonucu1 - savasSonucu2;
            double yuzde = (double) fark / 1000;
            int kaybedilenPopulasyon = (int) Math.round(digerKoloni.populasyon * yuzde);
            if (digerKoloni.populasyon * yuzde < 1) {
                kaybedilenPopulasyon = 1;
            }
            digerKoloni.populasyon -= kaybedilenPopulasyon;
            if (digerKoloni.populasyon < 1) {
                digerKoloni.populasyon = 0;
            }
            int kaybedilenYemek = (int) Math.round(digerKoloni.yemekStogu * yuzde);
            digerKoloni.yemekStogu -= Math.round(digerKoloni.yemekStogu * yuzde);
            if (digerKoloni.yemekStogu < 1) {
                digerKoloni.yemekStogu = 0;
            }
            yemekStogu += kaybedilenYemek;
            
            // System.out.println(this.sembol + " kolonisi kazandı. " + digerKoloni.getSembol() + " kolonisinden " + this.sembol + " Kolonisine geçen yemek stoğu: " + kaybedilenYemek);
            // System.out.println(this.sembol + " Stoğunun güncellenmiş hali: " + this.yemekStogu);
            // System.out.println(digerKoloni.getSembol() + " Stoğunun güncellenmiş hali: " + digerKoloni.getYemekStogu());
            // System.out.println(digerKoloni.getSembol() + " kolonisinin kaybettiği popülasyon sayısı: " + kaybedilenPopulasyon);
            // System.out.println(digerKoloni.getSembol() + " kolonisinin yeni popülasyon sayısı: " + digerKoloni.getPopulasyon());

            kazanma++; // Koloni1'in kazanma sayısını güncelle
            digerKoloni.kaybetme++; // Koloni2'nin kaybetme sayısını güncelle

        } else {
            // Koloni2 kazandı
            int fark = savasSonucu2 - savasSonucu1;
            double yuzde = (double) fark / 1000;
            int kaybedilenPopulasyon = (int) Math.round(populasyon * yuzde);
            if (populasyon * yuzde < 1) {
                kaybedilenPopulasyon = 1;
            }
            populasyon -= kaybedilenPopulasyon;
            if (populasyon < 1) {
                populasyon = 0;
            }
            int kaybedilenYemek = (int) Math.round(yemekStogu * yuzde);
            yemekStogu -= Math.round(yemekStogu * yuzde);
            if (yemekStogu < 1) {
                yemekStogu = 0;
            }
            digerKoloni.yemekStogu += kaybedilenYemek;
            
            // System.out.println(digerKoloni.getSembol() + " kolonisi kazandı. " + this.sembol + " kolonisinden " + digerKoloni.getSembol() + " Kolonisine geçen yemek stoğu: " + kaybedilenYemek);
            // System.out.println(digerKoloni.getSembol() + " Stoğunun güncellenmiş hali: " + digerKoloni.getYemekStogu());
            // System.out.println(this.sembol + " Stoğunun güncellenmiş hali: " + this.yemekStogu);
            // System.out.println(this.sembol + " kolonisinin kaybettiği popülasyon sayısı: " + kaybedilenPopulasyon);
            // System.out.println(this.sembol + " kolonisinin yeni popülasyon sayısı: " + this.populasyon);

            kaybetme++; // Koloni1'in kaybetme sayısını güncelle
            digerKoloni.kazanma++; // Koloni2'nin kazanma sayısını güncelle
            
        }
    }

    public boolean hayattaMi() {
        return populasyon > 0 && yemekStogu > 0;
    }
}