#include "e1.h"
#include <immintrin.h>


// ---------------------------------------------------------------------
// matmul
// ---------------------------------------------------------------------
// SSE version

//Codice da vettorizzare
/*
 * static void add_prod_seq(const short* src, short* dst, short x, int n) {
 	int j;
 	for (j=0; j < n; ++j) dst[j] += x * src[j];
 }
 * */
// 1 Faccio loop unrolling di dimensione k

static void add_prod(const short* src, short* dst, short x, int n) {
	long j;
	__m128i  srcV, xV, prodV, dstV  ;
	
	xV = _mm_set_epi16(x,x,x,x,x,x,x,x);


	for(j = 0; j+7 < n ; j+=8){
		dstV = _mm_loadu_si128((const __m128i*)(dst + j));
		srcV = _mm_loadu_si128((const __m128i*)(src + j));
		
		prodV = _mm_mullo_epi16(xV , srcV);
		dstV = _mm_add_epi16(dstV, prodV );

		_mm_storeu_si128((__m128i*) (dst+j), dstV);

	}
	for(; j<n ; j++) dst[j] += x * src[j];

}

void matmul(const short** a, const short** b, short** c, int n) {
    int i, j, k;
    for (i=0; i<n; ++i)
        for (j=0; j<n; ++j) c[i][j] = 0;
    for (i=0; i<n; ++i)
        for (k=0; k<n; ++k)
            add_prod(b[k], c[i], a[i][k], n);
}


// ---------------------------------------------------------------------
// matmul_seq
// ---------------------------------------------------------------------
// sequential version

static void add_prod_seq(const short* src, short* dst, short x, int n) {
    int j;
    for (j=0; j<n; ++j) dst[j] += x * src[j];
}

void matmul_seq(const short** a, const short** b, short** c, int n) {
    int i, j, k;
    for (i=0; i<n; ++i)
        for (j=0; j<n; ++j) c[i][j] = 0;
    for (i=0; i<n; ++i)
        for (k=0; k<n; ++k) 
            add_prod_seq(b[k], c[i], a[i][k], n);
}
