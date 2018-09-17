import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { AdminService } from "../admin.service";

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.css"]
})
export class AdminComponent implements OnInit {
  dateOfDelivery: any;
  slt: any;
  stopd: any;
  timeslot: any;
  slottt: any;
  slot: any;
  slotVehicle: any;
  slotOne: any;
  slotVehicleRoute: any;
  geo = {
    latitude: "",
    longitude: ""
  };
  public lat: string;
  public lng: string;

  public origin: any;
  public stops = [];
  public destination: any;
  slotVehicleRouteLength:any;
  item = {
    slot:1,
    vehicleId:1
  }
  vol:any;
  volume=0; 
  public doughnutChartLabels:string[] = ['Filled', 'Empty'];
  public doughnutChartData:number[];
  public doughnutChartType:string = 'doughnut';

  constructor(private http: HttpClient, private admin: AdminService) {}

  ngOnInit() {}
  onSubmit() {
    this.item.slot=this.timeslot;
    this.item.vehicleId=this.slot;
    this.admin.getRoute(this.item).subscribe(data => {
      this.slotVehicleRoute = data;
      this.slotVehicleRouteLength=data.length;     
    });

    this.admin.getTotalVolume(this.item).subscribe(data =>{
      this.vol=data;
    });
    setTimeout(() => {
      this.routes();
    }, 2000);
    setTimeout(() => {
      this.totalVolume();
    }, 2000);
  }

  routes() {
    this.origin = this.destination = { lat: 12.9266122, lng: 77.6934768 };
    for (let i = 0; i < this.slotVehicleRouteLength; i++) {
      this.stops[i] = {
        location: {
          lat: parseFloat(this.slotVehicleRoute[i].orderLocation.orderLatitude),
          lng: parseFloat(this.slotVehicleRoute[i].orderLocation.orderLongitude)
        },
        stopover: true
      };
    }
    this.stops.join();
    console.log(this.stops);
  }

  totalVolume() {

      this.volume = this.vol.vehicleLoadedCapacity;
      console.log(this.volume);
      this.doughnutChartData= [this.volume,5670000-this.volume];
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
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }

}
