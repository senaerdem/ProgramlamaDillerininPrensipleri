/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/

#include "Koloni.h"
#include <stdlib.h>

Koloni *koloni_olustur(int populasyon, char sembol, Taktik *taktik, Uretim *uretim) {
    Koloni *koloni = (Koloni *)malloc(sizeof(Koloni));
    koloni->populasyon = populasyon;
    koloni->yemek_stogu = populasyon * populasyon;
    koloni->sembol = sembol;
    koloni->taktik = taktik;
    koloni->uretim = uretim;
    //koloni->yasayan = 1;

    // başlangıç değerlerini atayalım
    koloni->kazanma = 0;
    koloni->kaybetme = 0;
    koloni->taktik_deger = 0;
    koloni->yasayan = populasyon;  // başlangıçta tüm popülasyon yaşıyor
    return koloni;
}

Koloni *koloni_kopyala(Koloni *orjinal) {
    Koloni *kopya = (Koloni *)malloc(sizeof(Koloni));
    *kopya = *orjinal; // Orjinal koloninin değerlerini kopya koloniye kopyala

    // Taktik ve Uretim nesnelerinin kopyalarını oluştur
    kopya->taktik = (Taktik *)malloc(sizeof(Taktik));
    *(kopya->taktik) = *(orjinal->taktik);
    kopya->uretim = (Uretim *)malloc(sizeof(Uretim));
    *(kopya->uretim) = *(orjinal->uretim);
    return kopya;
}

void koloni_yok_et(Koloni *koloni) {
    if (koloni) {
        if(koloni->taktik) {
            free(koloni->taktik);
        }

        if(koloni->uretim) {
            free(koloni->uretim);
        }

        free(koloni);
    }
}
