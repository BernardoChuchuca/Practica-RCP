import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private apiUrl = 'http://localhost:8080/jboss-javaee-webapp/rest/members'; 

  constructor(private http: HttpClient) { }

  // Método para registrar un miembro
  registrarMember(email: string, name: string, phone: string): Observable<any> {
    const member = { email, name, phoneNumber: phone };
    return this.http.post(this.apiUrl, member, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  lista: any[] = [];

  async listarProducto() {
    let response = await axios.get('http://localhost:8080/jboss-javaee-webapp/rest/members');
    this.lista = response.data;
    console.log(this.lista);
    return this.lista;
  }
}
