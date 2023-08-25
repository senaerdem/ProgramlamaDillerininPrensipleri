/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#include "Oyun.h"

Oyun *oyun_olustur(Koloni **koloniler, int koloni_sayisi) {
    Oyun *oyun = (Oyun *)malloc(sizeof(Oyun));
    oyun->koloni_sayisi = koloni_sayisi;
    oyun->koloniler = (Koloni **)malloc(koloni_sayisi * sizeof(Koloni *));
    for(int i = 0; i < koloni_sayisi; i++) {
        oyun->koloniler[i] = koloni_kopyala(koloniler[i]); // koloni_kopyala bir Koloni'yi kopyalayan bir fonksiyon olmalı
    }
    oyun->tur_sayisi = 0;
    return oyun;
}

void oyun_baslat(Oyun *oyun) {

    srand(time(NULL));
    int kalan_koloni_sayisi = oyun->koloni_sayisi;

    while (kalan_koloni_sayisi > 1) {
        // Her koloni için popülasyonu %20 artır ve yemek stoğunu (GüncelPopülasyon x 2) azalt
        // Savaşları gerçekleştir ve sonuçlara göre popülasyon ve yemek stoğu değiştir
        // Yemek stoğu veya popülasyonu sıfıra veya eksiye düşen kolonilerin yaşamını sonlandır

        // Mevcut turun sonuçlarını ekrana yazdırma
        printf("Tur Sayısı: %d\n", oyun->tur_sayisi);
        printf("Koloni\tPopülasyon\tYemek Stoğu\tKazanma\tKaybetme\n");

        for (int i = 0; i < oyun->koloni_sayisi; i++) {
            Koloni *koloni = oyun->koloniler[i];
            if (koloni->yasayan) {
                printf("%c\t%d\t\t%d\t\t%d\t%d\n", koloni->sembol, koloni->populasyon, koloni->yemek_stogu, koloni->kazanma, koloni->kaybetme);
                koloni->populasyon = (int)(koloni->populasyon * 1.2);
                // printf("Popülasyon yüzde 20 arttı. Yeni popülasyon: %d\n", koloni->populasyon);
                koloni->yemek_stogu -= koloni->populasyon * 2;
                // printf("Yemek stoğu (güncel popülasyon x 2) oranında azaldı. Yeni yemek stoğu: %d\n", koloni->yemek_stogu);
                int uretilen_yemek = koloni->uretim->uret(koloni->uretim);
                koloni->yemek_stogu += uretilen_yemek;
                // printf("Koloni %c, bu turda %d miktarda yemek üretti.\n", koloni->sembol, uretilen_yemek);
            }else {
                printf("%c\t—\t\t—\t\t—\t—\n", koloni->sembol);
            }
        }
        printf("-------------------------------------------------------------\n");
        oyun->tur_sayisi++;

        // Savaşları gerçekleştir ve sonuçlara göre popülasyon ve yemek stoğu değiştir
        for (int i = 0; i < oyun->koloni_sayisi; i++) {
            Koloni *koloni1 = oyun->koloniler[i];
            if (!koloni1->yasayan) continue;

            for (int j = i + 1; j < oyun->koloni_sayisi; j++) {
                Koloni *koloni2 = oyun->koloniler[j];
                if (!koloni2->yasayan) continue;
                // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni1->sembol, koloni1->yemek_stogu, koloni1->populasyon);
                // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni2->sembol, koloni2->yemek_stogu, koloni2->populasyon);
                // Savaş mekaniğini ve sonuçlarını işleyen kodlar
                int savas_sonucu1 = koloni1->taktik->savas(koloni1->taktik);
                int savas_sonucu2 = koloni2->taktik->savas(koloni2->taktik);

                // printf("Koloni %c, bu savaşta %d değerini üretti.\n", koloni1->sembol, savas_sonucu1);
                // printf("Koloni %c, bu savaşta %d değerini üretti.\n", koloni2->sembol, savas_sonucu2);

                if (savas_sonucu1 > savas_sonucu2 || (savas_sonucu1 == savas_sonucu2 && koloni1->populasyon > koloni2->populasyon)) {
                    // Koloni1 kazandı
                    int fark = savas_sonucu1 - savas_sonucu2;
                    double yuzde = (double)fark / 1000;
                    int kaybedilen_populasyon = round(koloni2->populasyon * yuzde);
                    if(koloni2->populasyon * yuzde < 1) {
                        kaybedilen_populasyon = 1;
                    }
                    koloni2->populasyon -= kaybedilen_populasyon;
                    if(koloni2->populasyon < 1) {
                        koloni2->populasyon = 0;
                    }
                    int kaybedilen_yemek = round(koloni2->yemek_stogu * yuzde);
                    koloni2->yemek_stogu -= round(koloni2->yemek_stogu * yuzde);
                    if(koloni2->yemek_stogu < 1){
                        koloni2->yemek_stogu = 0;
                    }
                    koloni1->yemek_stogu += kaybedilen_yemek;
                    
                    koloni1->kazanma++; // Koloni1'in kazanma sayısını güncelle
                    koloni2->kaybetme++; // Koloni2'nin kaybetme sayısını güncelle

                    // printf("Koloni %c, bu savaşta kaybettiği için %f yüzde oranında kayıp yaşadı. Bu kayıp, popülasyonunun %d ve yemek stoğunun %d kadar azalmasına yol açtı.\n", koloni2->sembol, yuzde*100, kaybedilen_populasyon, kaybedilen_yemek);
                    // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni1->sembol, koloni1->yemek_stogu, koloni1->populasyon);
                    // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni2->sembol, koloni2->yemek_stogu, koloni2->populasyon);

                    
                } else {
                    // Koloni2 kazandı
                    int fark = savas_sonucu2 - savas_sonucu1;
                    double yuzde = (double)fark / 1000;
                    int kaybedilen_populasyon = round(koloni1->populasyon * yuzde);
                    if(koloni1->populasyon * yuzde < 1) {
                        kaybedilen_populasyon = 1;
                    }
                    koloni1->populasyon -= kaybedilen_populasyon;
                    if(koloni1->populasyon < 1) {
                        koloni1->populasyon = 0;
                    }
                    int kaybedilen_yemek = round(koloni1->yemek_stogu * yuzde);
                    koloni1->yemek_stogu -= round(koloni1->yemek_stogu * yuzde);
                    if(koloni1->yemek_stogu < 1) {
                        koloni1->yemek_stogu = 0;
                    }
                    koloni2->yemek_stogu += kaybedilen_yemek;

                    koloni1->kaybetme++; // Koloni1'in kaybetme sayısını güncelle
                    koloni2->kazanma++; // Koloni2'nin kazanma sayısını güncelle

                    // printf("Koloni %c, bu savaşta kaybettiği için %f yüzde oranında kayıp yaşadı. Bu kayıp, popülasyonunun %d ve yemek stoğunun %d kadar azalmasına yol açtı.\n", koloni1->sembol, yuzde*100, kaybedilen_populasyon, kaybedilen_yemek);
                    // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni1->sembol, koloni1->yemek_stogu, koloni1->populasyon);
                    // printf("Koloni %c'nin mevcut yemek stoğu: %d, mevcut popülasyonu: %d\n", koloni2->sembol, koloni2->yemek_stogu, koloni2->populasyon);

                }
            }
        }

        // Yemek stoğu veya popülasyonu sıfıra veya eksiye düşen kolonilerin yaşamını sonlandır ve kalan koloni sayısını güncelle
        kalan_koloni_sayisi = 0;
        for (int i = 0; i < oyun->koloni_sayisi; i++) {
            Koloni *koloni = oyun->koloniler[i];
            if (koloni->yasayan && (koloni->yemek_stogu <= 0 || koloni->populasyon <= 0)) {
                koloni->yasayan = 0;
            }

            if (koloni->yasayan) {
                kalan_koloni_sayisi++;
            }
        }
    }

    // Sadece 1 koloni kaldığında
    printf("Tur Sayısı: %d\n", oyun->tur_sayisi);
    printf("Koloni\tPopülasyon\tYemek Stoğu\tKazanma\tKaybetme\n");
    for (int i = 0; i < oyun->koloni_sayisi; i++) {
        Koloni *koloni = oyun->koloniler[i];
        if (koloni->yasayan) {
            printf("%c\t%d\t\t%d\t\t%d\t%d\n", koloni->sembol, koloni->populasyon, koloni->yemek_stogu, koloni->kazanma, koloni->kaybetme);
        } else {
            printf("%c\t—\t\t—\t\t—\t—\n", koloni->sembol);
        }
    }
    printf("-------------------------------------------------------------\n");

}

// Oyun sonucunu ekrana yazdıran fonksiyon
void oyun_durumunu_yazdir(Oyun *oyun) {
    printf("Tur Sayısı: %d\n", oyun->tur_sayisi);
    printf("Koloni\tPopülasyon\tYemek Stoğu\tKazanma\tKaybetme\n");
    for (int i = 0; i < oyun->koloni_sayisi; i++) {
        Koloni *koloni = oyun->koloniler[i];
        if (koloni->yasayan) {
            printf("%c\t%d\t\t%d\t\t%d\t%d\n", koloni->sembol, koloni->populasyon, koloni->yemek_stogu, koloni->kazanma, koloni->kaybetme);
        } else {
            printf("%c\t—\t\t—\t\t—\t—\n", koloni->sembol);
        }
    }
    printf("-------------------------------------------------------------\n");
}

void oyun_sonlandir(Oyun *oyun) {
    if(oyun) {
        // Her koloniyi serbest bırak
        for (int i = 0; i < oyun->koloni_sayisi; i++) {
            koloni_yok_et(oyun->koloniler[i]);
        }

        // koloniler dizisini serbest bırak
        free(oyun->koloniler);

        // Oyun nesnesini serbest bırak
        free(oyun);
    }
}