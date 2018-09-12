export class Order {
    constructor(
        public orderId:  string,
        public orderConsumerAddress: string,       
        public orderLatitude:string,
        public orderLongitude:string,
        public orderConsumerName:string,
        public orderVolume:string,
        public orderConsumerPhone:string,
        public orderDate :string,
    ) {

    }
}