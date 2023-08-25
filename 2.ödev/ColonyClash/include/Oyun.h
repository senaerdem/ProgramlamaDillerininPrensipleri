#ifndef OYUN_H
#define OYUN_H

#include "Koloni.h"

typedef struct Oyun Oyun;

struct Oyun
{
    Koloni **koloniler;
    int koloni_sayisi;
    int tur_sayisi;
};

Oyun *oyun_olustur(Koloni **koloniler, int koloni_sayisi);
void oyun_baslat(Oyun *oyun);
void oyun_sonlandir(Oyun *oyun);
void oyun_durumunu_yazdir(Oyun *oyun);

#endif // OYUN_H
