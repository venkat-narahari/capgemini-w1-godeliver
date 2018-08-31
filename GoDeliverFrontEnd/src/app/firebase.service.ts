import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument} from 'angularfire2/firestore';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  cart:AngularFirestoreCollection<Cart>;
  carts:Observable<Cart[]>;
  
  constructor(public fs:AngularFirestore) { 

    this.cart=this.fs.collection('carts');
    // this.carts=this.fs.collection('cart').valueChanges();
    this.carts = this.cart.snapshotChanges().pipe(
      map(changes => changes.map(a => {
        const data = a.payload.doc.data() as Cart;
        const id = a.payload.doc.id;
        return data;
      })))
  }

  getCart() {
    return this.carts;
  }

addItem(carts:Cart) {
   this.cart.add(carts);
}

}

export interface Cart {
  bookISBN_10?:string;
  title?:string;
  cost?:string;
  genre?:string;
  poster?:string;

}