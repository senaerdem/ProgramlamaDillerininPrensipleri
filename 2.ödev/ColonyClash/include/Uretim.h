#ifndef URETIM_H
#define URETIM_H

typedef struct Uretim Uretim;

struct Uretim
{
    int (*uret)(Uretim *uretim);
};

Uretim *uretim_olustur(int (*uret)(Uretim *uretim));

#endif // URETIM_H
