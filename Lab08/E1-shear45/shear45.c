// =====================================================================
//  shear45.c
// =====================================================================

//  Author:         (c) 2018 --
//  License:        See the end of this file for license information
//  Created:        December 18, 2018
 
//  Last changed:   $Date: 2018/12/18 17:00:00 $
//  Changed by:     $Author: demetres $
//  Revision:       $Revision: 1.00 $


#include "shear45.h"

#define LOCAL_SIZE  8
#define KERNEL_NAME "shear45"

// ---------------------------------------------------------------------
// shear45
// ---------------------------------------------------------------------
// data-parallel GPU version

void shear45(unsigned char* in, unsigned char** out, 
             int h, int w, int* oh, int* ow,
             unsigned char gray,
             double* t, clut_device* dev) {
        
        int err;
        cl_kernel kernel;
        cl_mem din;
        cl_mem dout;
        cl_event evt;

        kernel = clCreateKernel(dev->program , KERNEL_NAME, &err);
        clut_check_err(err, "failed to create kernel");

        din = clCreateBuffer(dev->context ,
                        CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                        h*w*sizeof(unsigned char), in , NULL);
        if(!din) clut_panic("failed to allocate output matrix on device memory");

        err  = clSetKernelArg(kernel, 0, sizeof(cl_mem), &din);
        err |= clSetKernelArg(kernel, 1, sizeof(cl_mem), &dout);
        err |= clSetKernelArg(kernel, 2, sizeof(int), &h);
        err |= clSetKernelArg(kernel, 3, sizeof(int), &w);
        clut_check_err(err, "failed to set kernel arguments");

        size_t local_dim[]  = { LOCAL_SIZE, LOCAL_SIZE };
        size_t global_dim[] = { w, h };
        global_dim[0] = ((global_dim[0]+LOCAL_SIZE-1)/LOCAL_SIZE)*LOCAL_SIZE; // round up
        global_dim[1] = ((global_dim[1]+LOCAL_SIZE-1)/LOCAL_SIZE)*LOCAL_SIZE; // round up

        err = clEnqueueNDRangeKernel(dev->queue, kernel, 2, 
                                 NULL, global_dim, local_dim, 0, NULL, &evt);
        clut_check_err(err, "failed to execute kernel");

        // copy result from device to host
        err = clEnqueueReadBuffer(dev->queue, dout, CL_TRUE, 0, 
                                h*w*sizeof(unsigned char), O, 0, NULL, NULL);
        clut_check_err(err, "failed to read output result");

        // return kernel execution time
        *t = clut_get_duration(evt);

        // cleanup
        clReleaseMemObject(din);
        clReleaseMemObject(dout);
        clReleaseKernel(kernel);

}


// Copyright (C) 2018 --

// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.

// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.

// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
// USA
