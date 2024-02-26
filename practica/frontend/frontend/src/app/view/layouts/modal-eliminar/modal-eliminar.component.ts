import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-eliminar',
  templateUrl: './modal-eliminar.component.html',
  styleUrls: ['./modal-eliminar.component.css']
})
/*
  Clase que nos permite crear un cuadro de dialogo donde podresmos confirmar si eliminar
  o no un registro
*/
export class ModalEliminarComponent implements OnInit {

  mensaje: string;
  constructor(
    private dialogRef: MatDialogRef<ModalEliminarComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { mensaje: string }
  ) {}

  ngOnInit(): void {
    this.mensaje = this.data.mensaje;
  }

  cerrarModal(confirmacion: boolean) {
    this.dialogRef.close(confirmacion);
  }
  

}
