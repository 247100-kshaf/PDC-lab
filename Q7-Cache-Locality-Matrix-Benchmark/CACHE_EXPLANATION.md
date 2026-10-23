# CPU Cache Line and Spatial Locality

Modern CPUs do not fetch single values from RAM. They load a whole cache line
(typically 64 bytes, which is 8 doubles) into the L1/L2 cache at once.

In Java, a 2D array is an array of row arrays, and each row is stored as one
contiguous block of memory.

**Row-major traversal (i then j)** reads neighbouring elements one after another.
After the first element of a cache line is loaded, the next 7 doubles are already
in the cache. This is spatial locality, and the hardware prefetcher also predicts
this sequential pattern, so L1/L2 hit rates are high.

**Column-major traversal (j then i)** jumps from one row array to the next, so each
step lands in a different memory region (a stride of 2000 elements). Every access
tends to load a new cache line, use only 1 of its 8 doubles, and then move on. The
lines get evicted before they can be reused, causing many cache misses and stalling
the CPU pipeline while it waits for main memory.

Result: the same arithmetic on the same data runs noticeably slower in
column-major order.
