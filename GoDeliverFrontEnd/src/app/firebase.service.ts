import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument } from 'angularfire2/firestore';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class FirebaseService {


  cart: AngularFirestoreCollection<Cart>;
  carts: Observable<Cart[]>;
  cartDoc:AngularFirestoreDocument<Cart>;

  add: AngularFirestoreCollection<Address>;
  address: Observable<Address[]>;
  addDoc:AngularFirestoreDocument<Address>;


  constructor(public fs: AngularFirestore) {
    let idName='rajawatshivam007@gmail.com';
    this.cart = this.fs.collection('users/'+idName+'/cart');
    // this.carts=this.fs.collection('cart').valueChanges();
    this.carts = this.cart.snapshotChanges().pipe(
      map(changes => changes.map(a => {
        const data = a.payload.doc.data() as Cart;
        const id = a.payload.doc.id;
        return {id, ...data};
      })))

      this.add = this.fs.collection('users/'+idName+'/address');
      // this.carts=this.fs.collection('cart').valueChanges();
      this.address = this.add.snapshotChanges().pipe(
        map(changes => changes.map(a => {
          const data = a.payload.doc.data() as Address;
          const id = a.payload.doc.id;
          return {id, ...data};
        })))
     
  }

  getCart() {
    return this.carts;
  }

  addItem(carts: Cart) {
    this.cart.add(carts);
  }

  addAddress(address: Address) {
    this.add.add(address);
  }

  getAddress() {
    return this.address;
  }

  deleteItem(item:Cart) {
    let idNam='rajawatshivam007@gmail.com';
    this.cartDoc = this.fs.doc(`users/`+idNam+`/cart/${item.id}`);
    this.cartDoc.delete();
  }

  deleteAdd(item:Address) {
    let idNam='rajawatshivam007@gmail.com';
    this.cartDoc = this.fs.doc(`users/`+idNam+`/address/${item.id}`);
    this.cartDoc.delete();
  }
}

export interface Cart {
  bookISBN_10?: string;
  title?: string;
  cost?: string;
  genre?: string;
  poster?: string;
  id?:string;

}

export interface Address {
  address:string;
  id?:string;
}