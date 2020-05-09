#include <stdio.h>

int main(int argc, char const *argv[])
{
    #define N 5
    long array[ N ];
    long *lp;
    int *ip;
    int i;

    lp = array;
    for(i=0;i<N;i++){
        (*lp) = i;
        lp++;
    }

    ip = (int *)array;
    for(i=0;i<N;i++){
        (*ip) = i;
        ip++;
    }

    for (int i = 0; i < N; i++){
        printf("arr[%d] = %lx\n", i, array[i]);
    }

    // int ix = 0x8899aabb;
    // unsigned int uix = 0x8899aabb;
    // int iyr = ix >> 4;
    // unsigned int uiyr = uix >> 4;
    // long ly = ix;
    // unsigned long uly = uix;
    // long flag1 = (ly>uly) ? ly: uly;
    // long flag2;
    // if (ix >= uix)
    //     flag2 = 1;
    // else
    //     flag2 = 2;
    // long subtraction = ix;
    // subtraction = subtraction - 0x8899;
    // printf("iyr is %x\n", iyr);
    // printf("uiyr is %x\n", uiyr);
    // printf("flag1 is %lx\n", flag1);
    // printf("flag2 is %lx\n", flag2);
    // printf("sub is %lx\n", subtraction);
    // return 0;
}


    