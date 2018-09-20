import { Component, OnInit, Input } from "@angular/core";
import { AdminService } from "../admin.service";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-doughnut",
  templateUrl: "./doughnut.component.html",
  styleUrls: ["./doughnut.component.css"]
})
export class DoughnutComponent implements OnInit {
  dateOfDelivery: any;
  timeslot: any;
  slottt: any;
  slot: any;
  slotVehicle: any;
  slotOne: any;
  slotVehicleRoute: any;
  distance = [];
  slotVehicleRouteLength: any;
  item = {
    slot: 1,
    vehicleId: 1
  };
  vol: any;
  volume = 0;
  data: any;
  totalVolumeSlot:any;
  orderLength = [];
  
  timeSlotOne=0;
  timeSlotTwo=1;
  timeSlotThree=2;

  vehicleOne=0;
  vehicleTwo=1;
  vehicleThree=2;

  public doughnutChartLabels:string[] = ['Filled', 'Empty'];
  public doughnutChartData:number[];
  public doughnutChartType:string = 'doughnut';

  constructor(private http: HttpClient, private admin: AdminService) {}

  ngOnInit() {}
  onSubmit() {
    this.item.slot = this.timeslot;
    this.item.vehicleId = this.slot;
    this.dateOfDelivery = this.dateOfDelivery;
    this.admin.getRoute(this.item).subscribe(data => {
      this.slotVehicleRoute = data;
      this.slotVehicleRouteLength = data.length;
    });

    this.admin.getAllVolume(this.item).subscribe(data => {
      this.totalVolumeSlot = data;
      this.slotVehicleRouteLength = data.length;
    });

    this.admin.getTotalVolume(this.item).subscribe(data => {
      this.vol = data;
    });
    setTimeout(() => {
      this.totalVolume();
    }, 2000);

    this.admin.getTotalDistance(this.item).subscribe(data => {
      this.data = data;
      for (let i = 0; i < this.data.length; i++) {
        this.distance[i] = this.data[i].vehicleCoveredDistance;
        this.orderLength[i] = this.data[i].vehicleRoute.length;
      }
    });
    setTimeout(() => {
      this.totalVolume();
    }, 2000);


  }

  totalVolume() {
    this.volume = this.vol.vehicleLoadedCapacity;
    console.log(this.volume);
    this.doughnutChartData = [this.volume, 5670000 - this.volume];
    // this.distance=this.vol.vehicleCoveredDistance;
  }


  timePrint(item) {
    this.timeslot = item;
    console.log(this.timeslot);
  }
  slotPrint(item) {
    this.slot = item;
    console.log(this.slot);
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}
