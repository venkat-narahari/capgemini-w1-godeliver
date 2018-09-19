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
    vehicleId:1,
  }
  vol:any;
  volume=0; 
  distance = [];
  data: any;
  orderLength = [];
  public doughnutChartData:number[];

  constructor(private http: HttpClient, private admin: AdminService) {}

  ngOnInit() {}
  onSubmit() {
    this.item.slot=this.timeslot;
    this.item.vehicleId=this.slot;
    this.dateOfDelivery=this.dateOfDelivery;
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

    this.admin.getTotalDistance(this.item).subscribe(data =>{
      this.data=data;
      for (let i=0; i<this.data.length; i++){
        this.distance[i]=this.data[i].vehicleCoveredDistance; 
        this.orderLength[i] = this.data[i].vehicleRoute.length;
     }

      
    });
    setTimeout(() => {
      this.totalVolume();
    }, 2000);
  }


  


  routes() {
    this.origin =  { lat: 12.9266122, lng: 77.6934768 };
    this.destination = { lat: 12.9266115, lng: 77.6934758 };
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
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }

onDate(){
  console.log(this.dateOfDelivery);
  
}



public barChartOptions:any = {
  scaleShowVerticalLines: false,
  responsive: true
};
public barChartLabels:string[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
public barChartType:string = 'bar';
public barChartLegend:boolean = true;

public barChartData:any[] = [
  {data:this.distance, label: 'Vehicle Covered Distance'},
  {data:this.orderLength, label: 'No. of Orders Placed'}
];
}