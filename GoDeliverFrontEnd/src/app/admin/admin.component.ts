import { Slot } from "./../Model/Slot";
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
  slotVehicleRouteLength: any;
  item = {
    slot: 1,
    vehicleId: 1
  };
  vol: any;
  slot1Capacity: number = 0;
  slot2Capacity: number = 0;
  slot3Capacity: number = 0;
  volume = 0;
  distance1 = [0, 0, 0];
  distance2 = [0, 0, 0];
  distance3 = [0, 0, 0];
  orderLength1 = [0, 0, 0];
  orderLength2 = [0, 0, 0];
  orderLength3 = [0, 0, 0];
  data: any;
  slotData: any;
  data11: any;
  orderLength = [];
  public doughnutChartLabels: string[] = ["Filled", "Empty"];
  public doughnutChartData: number[];
  public doughnutChartType: string = "doughnut";

  public barChartLabels: string[] = ["Vehicle 1", "Vehicle 2", "Vehicle 3"];
  public barChartType: string = "bar";
  public barChartLegend: boolean = true;
  public barChartData1: any[] = [
    { data: this.distance1, label: "Vehicle Covered Distance" },
    { data: this.orderLength1, label: "No. of Orders Placed" }
  ];
  public barChartData2: any[] = [
    { data: this.distance2, label: "Vehicle Covered Distance" },
    { data: this.orderLength2, label: "No. of Orders Placed" }
  ];
  public barChartData3: any[] = [
    { data: this.distance3, label: "Vehicle Covered Distance" },
    { data: this.orderLength3, label: "No. of Orders Placed" }
  ];

  public pieChartLabels: string[] = ["LoadedCapacity", "UnLoadedCapacity"];
  public pieChartData1: number[];
  public pieChartData2: number[];
  public pieChartData3: number[];
  public pieChartType: string = "pie";

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

    this.admin.getTotalVolume(this.item).subscribe(data => {
      this.vol = data;
    });

    // this.admin.getTotalDistance(this.item).subscribe(data =>{
    //   this.data=data;
    //   for (let i=0; i<this.data.length; i++){
    //     this.distance[i]=this.data[i].vehicleCoveredDistance;
    //     this.orderLength[i] = this.data[i].vehicleRoute.length;
    //  }
    // });

    setTimeout(() => {
      this.routes();
    }, 2000);
    setTimeout(() => {
      this.totalVolume();
    }, 2000);

    this.admin.getSlotData(this.dateOfDelivery).subscribe(data => {
      this.data = data;
      for (let j = 0; j < this.data[0].slotVehicle.length; j++) {
        this.slot1Capacity =this.slot1Capacity + parseInt(this.data[0].slotVehicle[j].vehicleLoadedCapacity);
        this.distance1[j] = this.data[0].slotVehicle[j].vehicleCoveredDistance;
        this.orderLength1[j] = this.data[0].slotVehicle[j].vehicleRoute.length;
        // orderLength=this.data[0].slotVehicle[j].vehicleRoute.length;
      }

      for (let j = 0; j < this.data[0].slotVehicle.length; j++) {
        this.slot2Capacity =
          this.slot2Capacity + parseInt(this.data[1].slotVehicle[j].vehicleLoadedCapacity);
        this.distance2[j] = this.data[1].slotVehicle[j].vehicleCoveredDistance;
        this.orderLength2[j] = this.data[0].slotVehicle[j].vehicleRoute.length;
      }
      for (let j = 0; j < this.data[0].slotVehicle.length; j++) {
        this.slot3Capacity =
          this.slot3Capacity +
          parseInt(this.data[2].slotVehicle[j].vehicleLoadedCapacity);
        this.distance3[j] = this.data[2].slotVehicle[j].vehicleCoveredDistance;
        this.orderLength3[j] = this.data[0].slotVehicle[j].vehicleRoute.length;
      }
      this.pieChartData1 = [this.slot1Capacity, 17010000 - this.slot1Capacity];
      console.log(this.pieChartData1);
      this.pieChartData2 = [this.slot2Capacity, 17010000 - this.slot2Capacity];
      console.log(this.pieChartData2);
      this.pieChartData3 = [this.slot3Capacity, 17010000 - this.slot3Capacity];
      console.log(this.pieChartData3);
    });
  }

  routes() {
    this.origin = { lat: 12.9266122, lng: 77.6934768 };
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
  }

  totalVolume() {
    this.volume = this.vol.vehicleLoadedCapacity;
    this.doughnutChartData = [this.volume, 5670000 - this.volume];
  }

  timePrint(item) {
    this.timeslot = item;
  }
  slotPrint(item) {
    this.slot = item;
  }

  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
}
