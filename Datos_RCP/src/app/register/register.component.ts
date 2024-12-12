import { Component } from '@angular/core';
import { MemberService } from '../member.service';
import { Miembro } from './miembro';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email: string = '';
  name: string = '';
  phone: string = '';
  message: string = '';

  constructor(private memberService: MemberService) {}
  miembros:Miembro[]=[];
  ngOnInit(): void {
    this.getProductos();
  }

  async getProductos(){
    this.miembros= await this.memberService.listarProducto();
  }
  // Método para enviar el formulario
  async onRegister(): Promise<void> {
    if (this.email && this.name && this.phone) {
      this.memberService.registrarMember(this.email, this.name, this.phone).subscribe(
        response => {
          this.message = 'Miembro registrado con éxito!';
        },
        error => {
          console.error(error);
          this.message = 'Error al registrar el miembro.';
        }
      );
    } else {
      this.message = 'Por favor, complete todos los campos.';
    }
    this.miembros= await this.memberService.listarProducto();
  }

}
