package com.mitienda.spring.menus;


import java.util.ArrayList;
import java.util.List;

import com.mitienda.spring.controllers.CategoryController;
import com.mitienda.spring.controllers.ClienteController;
import com.mitienda.spring.controllers.FacturaController;
import com.mitienda.spring.controllers.FacturaLineaController;
import com.mitienda.spring.controllers.ProductoController;
import com.mitienda.spring.models.Categoria;
import com.mitienda.spring.models.Clientes;
import com.mitienda.spring.models.Factura;
import com.mitienda.spring.models.FacturaLinea;
import com.mitienda.spring.models.Producto;



public class MenuFacturas extends Menu {
	
	
	private static FacturaController facCtrl = FacturaController.getInstance();
	private static FacturaLineaController flCtrl = FacturaLineaController.getInstance();
	
	
	
	public String toString() {
		// TODO Auto-generated method stub
		return 
				"�Qu� acci�n quiere realizar? \n"+
				"1.Crear nueva factura \n"+
				"2.A�adir una linea a una factura existente \n"+
				"3.Ver facturas\n"+
				"4.Ver lineas de una factura\n"+
				"5.Editar una factura \n"+
				"6.Editar una linea de una factura \n"+
				"7.Borrar una factura \n"+	
				"8.Borrar una linea de una factura \n"+
				
				"0.Volver al men� principal"	;
		
	}
	
	public Menu siguienteMenu(String opcion) {
				
				switch (opcion) {
				case "1":
					crearFactura();
					break;
				case "2":
					crearLineaFactura();
					break;
				case "3": 
					verFacturas();
					break;
				case "4": 
					verLineasFactura();
					break;
				case "5":
					editarFactura();
					break;
				case "6":
					editarLineaFactura();
					break;
				case "7":	
					borrarFactura();
					break;		
				case "8":
					borrarLineaFactura();
					break;
				case "0":
					return new MenuPrincipal();
				default:
					System.out.println("Opci�n no v�lida");
					break;
				
				}
		return new MenuFacturas();		
	}
	
	
	
	private void editarLineaFactura() {
		FacturaLinea fl = MenuController.eligeLineaFactura();
		
		if( fl == null) {
			System.out.println("Linea de factura no encontrada");
			return;
		}
		Factura fac = MenuController.eligeFactura();
		if( fac == null) {
			return;
		}
		
		fl.setId_factura(fac.getId());
		Producto prod = MenuController.eligeProducto();		
		fl.setNombre(prod.getNombre());
		fl.setPrecio(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		
		
	}

	private void borrarLineaFactura() {
		
		FacturaLinea fl = MenuController.eligeLineaFactura();
		flCtrl.delete(fl);
		
	}

	private void borrarFactura() {
		Factura fac = MenuController.eligeFactura();
		List<FacturaLinea> listAllFl = flCtrl.list();
		List<FacturaLinea> listFl = new ArrayList<>();
		
		
		for(FacturaLinea obj: listAllFl) {
			if(obj.getId_factura() == fac.getId()) {
				listFl.add(obj);
			}
		}
		
		for(FacturaLinea obj: listFl) {
			flCtrl.delete(obj);
		}
		
		facCtrl.delete(fac);
		
	}

	private void editarFactura() {
		
		Factura fac = MenuController.eligeFactura();
		if (fac == null) {
			System.out.println("La factura no existe");			
			return ;
		}
		
		System.out.println("Elija un cliente al que asignar la factura");
		Clientes cli = MenuController.eligeCliente();
		if (cli== null) {
			System.out.println("El cliente no existe");
			return;
		}
		
		System.out.println("Introduzca la fecha de la factura, (formato yyyy/MM/dd) ");
		fac.setFecha(MenuController.validarFecha());		
		System.out.println("Introduzca la serie de la factura,  ");
		fac.setSerie(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		fac.setId_cliente(cli.getId());
	}
		
	

	private void verLineasFactura() {
		Factura fac = MenuController.eligeFactura();
		
		List<FacturaLinea> listAllFl = flCtrl.list();
		List<FacturaLinea> listFl = new ArrayList<>();
		
		
		for(FacturaLinea obj: listAllFl) {
			if(obj.getId_factura() == fac.getId()) {
				listFl.add(obj);
			}
		}
		
		for(FacturaLinea obj: listFl) {
			System.out.println(obj.getId()+" "+obj.toString());
		}
				
				
		
	}

	private void verFacturas() {
		List<Factura> facList = facCtrl.list();
		
		for(Factura obj: facList ) {
			System.out.println(obj.getId()+ " " +obj.toString());
		}
		
	}

	
	private void crearLineaFactura() {
		Factura fac = MenuController.eligeFactura();
		if( fac == null) {
			return;
		}
		FacturaLinea fl = new FacturaLinea();
		fl.setId_factura(fac.getId());
		Producto prod = MenuController.eligeProducto();		
		fl.setNombre(prod.getNombre());
		fl.setPrecio(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		
		flCtrl.save(fl);
	}

	private void crearFactura() {
		
		Factura fac = new Factura();
		

		System.out.println("Elija un cliente al que asignar la factura");
		Clientes cli = MenuController.eligeCliente();
		if (cli== null) {
			System.out.println("El cliente no existe");
			return;
		}
		
		System.out.println("Introduzca la fecha de la factura, (formato yyyy/MM/dd) ");
		fac.setFecha(MenuController.validarFecha());		
		System.out.println("Introduzca la serie de la factura,  ");
		fac.setSerie(Integer.parseInt(MenuController.campoValido("^\\d+$")));
		fac.setId_cliente(cli.getId());
			
		facCtrl.save(fac);
		
	}
	
	



}
