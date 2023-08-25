#ifndef KOLONI_H
#define KOLONI_H

#include "Taktik.h"
#include "Uretim.h"

typedef struct Koloni Koloni;

struct Koloni
{
    int kazanma;
    int kaybetme;
    int yasayan;
    int populasyon;
    int yemek_stogu;
    char sembol;
    Taktik *taktik;
    Uretim *uretim;
    int taktik_deger;
};

Koloni *koloni_olustur(int populasyon, char sembol, Taktik *taktik, Uretim *uretim);
void koloni_yok_et(Koloni *koloni);
Koloni *koloni_kopyala(Koloni *orjinal);

#endif // KOLONI_H
