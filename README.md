# parking-lot

## 需求：
停车场(Parking Lot)可以停车、取车;
不同的停车小哥(Parking Boy)能够依次或者按优先级(空置率、空闲车位数)在多个停车
场中停放车辆;
停车经理(Parking Manger)指挥多个停车小哥，也可以自己去停车。

### 任务分解（不做程序设计，只是理需求）：
停车场(Parking Lot)：

    停车
        停车场有停车位的时候，可以停车
            停车场是空的，可以停车
            停车场还有空位，可以停车
        停车场没有停车位的时候，不可以停车
    取车
        停车场有车的时候，可以取车
            拿凭条取车，返回该凭条对应的车
            如果凭条没有找到对应的车，不可以取车
            凭证已取车，不能再取
            
        
停车小哥(Parking Boy)：

    停车
        可以在多个停车场停车；
        可以根据停车场顺序依次停车；
        可以根据停车场空闲车位数多少（从高到底）依次停车；
        可以根据停车场空置率（从高到底）依次停车；
    取车
        没有需求？？？

停车经理(Parking Manger):

    停车
        停车经理可以自己去停车；
        停车经理可以指挥多个停车小哥停车；

