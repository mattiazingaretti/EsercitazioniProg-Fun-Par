

__kernel void shear45(__global unsigned char* I,
                      __global unsigned char* O, 
                      int h, int w  ) {
    
    int x = get_global_id(0);
    int y = get_global_id(1);

    if ( x >= w || y >= h) return;

    


}