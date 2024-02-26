import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-informacion',
  templateUrl: './modal-informacion.component.html',
  styleUrls: ['./modal-informacion.component.css']
})

/*
  Clase que nos permite crear un cuadro de dialogo donde enviar cualquier informacion
  y puede ser utilizada a conveniencia ya que solo muestra un mensaje
*/


export class ModalInformacionComponent implements OnInit {

  mensaje: string;
  constructor(
    private dialogRef: MatDialogRef<ModalInformacionComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { mensaje: string }
  ) {}

  ngOnInit(): void {
    this.mensaje = this.data.mensaje;
  }

  cerrarModal(confirmacion: boolean) {
    this.dialogRef.close(confirmacion);
  }
  

}
