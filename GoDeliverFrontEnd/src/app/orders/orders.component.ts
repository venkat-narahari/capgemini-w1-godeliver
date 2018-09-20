import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orderDetails:any;
  constructor(private orderService:OrderService,
    private router: ActivatedRoute
    ) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      const id = params['orderid'];
      this.orderService.getOrder(id).subscribe(data => {
        this.orderDetails = data;
        console.log(data);
        
      });
    });
  }

}
