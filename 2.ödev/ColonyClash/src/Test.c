/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include "Koloni.h"
#include "Oyun.h"
#include "ATaktik.h"
#include "BTaktik.h"
#include "AUretim.h"
#include "BUretim.h"

int *populasyonlari_al(int *koloni_sayisi) {
    char input[1024];
    fgets(input, sizeof(input), stdin);
    
    int *populasyonlar = (int *)malloc(1 * sizeof(int));
    *koloni_sayisi = 0;
    
    char *token = strtok(input, " ");
    while (token != NULL) {
        populasyonlar = (int *)realloc(populasyonlar, (*koloni_sayisi + 1) * sizeof(int));
        populasyonlar[*koloni_sayisi] = atoi(token);
        (*koloni_sayisi)++;
        token = strtok(NULL, " ");
    }

    return populasyonlar;
}

char rastgele_sembol_uret() {
    static char semboller[] = {'!', '#', '$', '%', '&', '*', '+', '?', '@', '^', '~', '-', '/'};
    static char kullanilan_semboller[sizeof(semboller)/sizeof(char)] = {0};
    static int koloni_index = 0;

    if (koloni_index >= sizeof(semboller)/sizeof(char)) {
        for (int i = 0; i < sizeof(semboller)/sizeof(char); i++) {
            kullanilan_semboller[i] = 0;
        }
        koloni_index = 0;
    }

    char sembol;
    do {
        sembol = semboller[rand() % sizeof(semboller)/sizeof(char)];
    } while (kullanilan_semboller[sembol]);

    kullanilan_semboller[sembol] = 1;
    koloni_index++;
    return sembol;
}

int main() {
    srand(time(NULL));

    printf("Popülasyonları girin (boşlukla ayrılmış şekilde): ");
    int koloni_sayisi;
    int *populasyonlar = populasyonlari_al(&koloni_sayisi);

    Koloni **koloniler = (Koloni **)malloc(koloni_sayisi * sizeof(Koloni *));

    for (int i = 0; i < koloni_sayisi; i++) {
        char sembol = rastgele_sembol_uret();
        int populasyon = populasyonlar[i];

        Taktik *taktik;
        if (rand() % 2 == 0) {
            taktik = (Taktik *)ataktik_olustur();
        } else {
            taktik = (Taktik *)btaktik_olustur();
        }

        Uretim *uretim;
        if (rand() % 2 == 0) {
            uretim = (Uretim *)auretim_olustur();
        } else {
            uretim = (Uretim *)buretim_olustur();
        }

        koloniler[i] = koloni_olustur(populasyon, sembol, taktik, uretim);
    }

    // Oyunu oluşturma ve başlatma
    Oyun *oyun = oyun_olustur(koloniler, koloni_sayisi);
    oyun_baslat(oyun);

    // Oyunun sonucunu (son turunu) yazdırır
    // oyun_durumunu_yazdir(oyun);

    // Oyunu sonlandırma ve belleği temizleme
    oyun_sonlandir(oyun);

    for (int i = 0; i < koloni_sayisi; i++) {
        koloni_yok_et(koloniler[i]);
    }
    free(koloniler);
    free(populasyonlar);

    return 0;
}