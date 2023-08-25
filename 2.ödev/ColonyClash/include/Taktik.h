#ifndef TAKTIK_H
#define TAKTIK_H

typedef struct Taktik Taktik;

struct Taktik
{
    int (*savas)(Taktik *taktik);
};

Taktik *taktik_olustur(int (*savas)(Taktik *taktik));

#endif // TAKTIK_H
