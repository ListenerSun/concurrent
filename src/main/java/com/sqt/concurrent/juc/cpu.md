###CPU多级缓存
- 为什么需要CPU cache：CPU的频率太快了,快到主存跟不上,这样在处理器时钟周期内，
                       CPU常常需要等待主存，浪费资源。所以cache的出现是为了缓解
                       CPU和内存之间速度的不匹配问题。
- CPU cache存在的意义:
    + 时间局部性:如果某个数据被访问,那么在不久的将来它很可能再次被访问。
    + 空间局部性:如果某个数据被访问,那么与它相邻的数据很快也可能被访问。
- CPU多级缓存——缓存一致性:   
    + MESI协议将cache line的状态分成modify、exclusive、shared、invalid，分别是修改、独占、共享和失效。
    + modify：当前CPU cache拥有最新数据（最新的cache line），其他CPU拥有失效数据（cache line的状态是invalid），
               虽然当前CPU中的数据和主存是不一致的，但是以当前CPU的数据为准。
    + exclusive：只有当前CPU中有数据，其他CPU中没有改数据，当前CPU的数据和主存中的数据是一致的。
    + shared：当前CPU和其他CPU中都有共同数据，并且和主存中的数据一致。
    
    
    
    + invalid：当前CPU中的数据失效，数据应该从主存中获取，其他CPU中可能有数据也可能无数据，当前CPU中的数据和主存被认为是不一致的。    
###Java内存模型