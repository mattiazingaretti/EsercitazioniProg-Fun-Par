#include <immintrin.h>
#include "e2.h"


// ---------------------------------------------------------------------
// count_occ
// ---------------------------------------------------------------------
// SSE version

/*
 * 

int count_occ_seq(const char* v, int n, char x) {
    int i, cnt = 0;
    for (i=0; i < n; ++i) cnt += v[i] == x;
    return cnt;
}
*/


int count_occ(const char* v, int n, char x) {
	
	__m128i vV, xV , equalV ,noneV,oneV,cntAV;
	
	xV = _mm_set1_epi8(x);
	
	noneV = _mm_set_epi8(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1);
    cntAV = _mm_set_epi8(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	oneV = _mm_set_epi8(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
	
	long  cnt = 0;
	unsigned char tmpCounter[16];
	long i,j;
	
	for(i = 0 ; i +15 < n ; i += 16){
		vV = _mm_load_si128((const __m128i*)(v+i));
		equalV = _mm_cmpeq_epi8 (vV, xV);
		equalV  = _mm_add_epi8(equalV, oneV);
        
        cntAV = _mm_add_epi8(equalV, noneV  ); //cntAv = equalV - cntAv
        
        //Per evitare overflow 
        if (i % 255 == 0) {
            _mm_storeu_si128((__m128i*)tmpCounter, cntAV);
            for (j=0; j<16; ++j) {
				cnt += tmpCounter[j];
            }
            cntAV = _mm_set_epi8(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }
        
	}
	
	//In caso n non è multiplo di 16
	for (; i<n; ++i)
        if (v[i] == x) ++cnt;
	
	 _mm_storeu_si128((__m128i*)tmpCounter, cntAV);
    for (j=0; j<16; ++j) {
			cnt += tmpCounter[j];
	}	
	return cnt;
}


// ---------------------------------------------------------------------
// count_occ_seq
// ---------------------------------------------------------------------
// sequential version

int count_occ_seq(const char* v, int n, char x) {
    int i, cnt = 0;
    for (i=0; i<n; ++i) cnt += v[i] == x;
    return cnt;
}
